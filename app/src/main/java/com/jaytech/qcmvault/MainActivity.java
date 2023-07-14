package com.jaytech.qcmvault;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	int check = 0;
	private static final String PREFS_NAME = "MyPrefs";
    private static final String METHOD_RUN_KEY = "hasMethodRun";
	String selectedYear,session,moduleKey,annee,semester;
	private boolean hasMethodRun;
	ShimmerFrameLayout shimmer;
    DatabaseReference database;
    MyAdapter adapter;
    List<Item> items;
	private int childCount = 0;
    RecyclerView recyclerView;
	CheckBox a;
	SharedPreferences prefs;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
		
		prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		hasMethodRun = prefs.getBoolean(METHOD_RUN_KEY, false);
		
		shimmer = findViewById(R.id.shimmerview);
		selectedYear = getIntent().getStringExtra("selectedYear");
		session = getIntent().getStringExtra("selectedSession");
		moduleKey = getIntent().getStringExtra("modulekey");
		annee = getIntent().getStringExtra("year");
		semester = getIntent().getStringExtra("semester");
		String moduleName = getIntent().getStringExtra("modulename");
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.action_bar_main); // Replace with your custom layout file

		TextView titleTextView = actionBar.getCustomView().findViewById(R.id.title_text_view); // Replace with the ID of your title TextView
		titleTextView.setText(moduleName);
		titleTextView.setGravity(Gravity.CENTER); // Center alignment

		actionBar.setDisplayShowTitleEnabled(false);
		
		//shimmer.startShimmerAnimation();
        recyclerView = findViewById(R.id.recyclerview);
		recyclerView.setVisibility(View.GONE);
		//killShimmer();

		
//		
        items = new ArrayList<>();
        adapter = new MyAdapter(getApplicationContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //fetch questions from firebase and assign to items
        fetchDataFromFirebase();
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
//		SharedPreferences.Editor editor = prefs.edit();
//		editor.putBoolean(METHOD_RUN_KEY,false);
//		editor.apply();
	}
	


	public void runOnceMethod(){
		loadShimmer();
	}
	
	//stops shimmer animation
	public void loadShimmer() {
		shimmer.startShimmerAnimation();
		
		Handler handler = new Handler();
		Runnable runnable = new Runnable(){
			@Override
			public void run() {
				shimmer.stopShimmerAnimation();
				shimmer.setVisibility(View.GONE);
				recyclerView.setVisibility(View.VISIBLE);
			}
		};
		handler.postDelayed(runnable, 1000);
	}

//firebase call
	public void fetchDataFromFirebase() {
		database = FirebaseDatabase.getInstance().getReference().child("years").child(annee).child("semesters").child(semester).child("modules").child(moduleKey).child(session).child(selectedYear).child("questions");
        // Retrieve the questions from the database
		database.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
					items.clear(); // Clear the previous items

					int questionNumber = 1;
					int totalChildren = (int) dataSnapshot.getChildrenCount();
					// Iterate through the questions in ascending order
					while (dataSnapshot.hasChild("question" + questionNumber)) {

						DataSnapshot questionSnapshot = dataSnapshot.child("question" + questionNumber);
						String questionText = questionSnapshot.child("question").getValue(String.class);

						List<String> answers = new ArrayList<>();
						DataSnapshot answersSnapshot = questionSnapshot.child("answers");
						for (DataSnapshot answerSnapshot : answersSnapshot.getChildren()) {
							String answer = answerSnapshot.getValue(String.class);
							answers.add(answer);
						}

						List<Boolean> correctAnswers = new ArrayList<>();
						DataSnapshot correctAnswersSnapshot = questionSnapshot.child("correctAnswers");
						for (DataSnapshot correctAnswerSnapshot : correctAnswersSnapshot.getChildren()) {
							Boolean correctAnswer = correctAnswerSnapshot.getValue(Boolean.class);
							correctAnswers.add(correctAnswer);
						}

						items.add(new Item(questionText, answers, correctAnswers));

						questionNumber++;
						childCount++;
						Log.d("count", "count" + totalChildren + " " + childCount);

						if (childCount < totalChildren) {
							Log.d("if", "inside if");
						} 
//						if (check == 0) {
//							shimmer.startShimmerAnimation();
//							shimmer.setVisibility(View.VISIBLE);
//							recyclerView.setVisibility(View.GONE);
//							check++;
//						}else {
//							shimmer.stopShimmerAnimation();
//							shimmer.setVisibility(View.GONE);
//							recyclerView.setVisibility(View.VISIBLE);
//
//						}
						
					}

					adapter.notifyDataSetChanged();
					
					//shimmer.setVisibility(View.VISIBLE);

					if(hasMethodRun){
						shimmer.setVisibility(View.GONE);
					}

					if (!hasMethodRun) {
						runOnceMethod();

						// Update the flag state in shared preferences
						SharedPreferences.Editor editor = prefs.edit();
						editor.putBoolean(METHOD_RUN_KEY, true);
						editor.apply();
					}else{
						recyclerView.setVisibility(View.VISIBLE);
						shimmer.setVisibility(View.GONE);
					}

				}

				@Override
				public void onCancelled(DatabaseError databaseError) {
					Log.e("TAG", "Error retrieving value", databaseError.toException());
				}
			});


	}

}

