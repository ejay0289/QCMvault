package com.jaytech.qcmvault;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ModuleActivity extends Activity {
	
	 private String selectedYear;
	private String selectedSession;
	private String selectedSemester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity);
		//Get stringExtras from AnneeActivity
		final String year = getIntent().getStringExtra(AnneeActivity.extraYear);
		final String semester = getIntent().getStringExtra(AnneeActivity.extraSemester);
		String titleYear = getIntent().getStringExtra("extraAnnee");
		 TextView moduleTitle = findViewById(R.id.moduleTitle);
		 moduleTitle.setText(titleYear);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.action_bar_main); // Replace with your custom layout file
actionBar.hide();
		getWindow().setFlags(
			WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
			WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
		);
		//TextView titleTextView = actionBar.getCustomView().findViewById(R.id.title_text_view); // Replace with the ID of your title TextView
		//titleTextView.setText(titleYear);
		// Center alignment

// Optional: If you want to hide the default title
		//actionBar.setDisplayShowTitleEnabled(false);
		
		
		//Getting display size
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		
		
		//Assigning views
		Spinner sessionSpinner = findViewById(R.id.sessionspinner);
		final Spinner yearSpinner = findViewById(R.id.yearspinner);
		final Button button1 = findViewById(R.id.button1);
		final Button button2 = findViewById(R.id.button2);
		final Button button3 = findViewById(R.id.button3);
		final Button button4 = findViewById(R.id.button4);
		final Button button5 = findViewById(R.id.button5);
		final Button button6 = findViewById(R.id.button6);
		
		final String[] sessions = getResources().getStringArray(R.array.sessions);
		final String[] years = {"2022","2021","2020","2019","2018","2017"};
		
		
//		ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(years));
	ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,R.layout.xspinner_item,sessions);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	sessionSpinner.setAdapter(adapter2);
//		
		//TextView textView = sessionSpinner.findViewById(android.R.id.text1);
		//textView.setTextColor(Color.RED); // Replace `Color.RED` with the desired color

       // Create an ArrayAdapter and set it as the adapter for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.xspinner_item, years);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter);
		
		//ArrayAdapter adapter = new ArrayAdapter(ModuleActivity.this,android.R.layout.simple_spinner_item,sessions);
		
		
		
		
		
		
		
		
		
		final String[] s3ModuleKeys = {"anatomie","microbiologie","physiologie1","secourismeMedex","histoEmbryologie","semiologie1"};
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//sessionSpinner.setAdapter(adapter);
		//Set button texts
		Drawable drawable;
		int width = 110;
		int height = 110;
		switch(semester){
				
			case "semester1":
				button1.setText("Anatomie");
				drawable= getResources().getDrawable(R.drawable.anatomie);
				 // Adjust the desired width
				// Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button1.setCompoundDrawables(null, drawable, null, null);
				button1.setCompoundDrawablePadding(0);  // Adjust the desired padding
				
				button2.setText("Biologie");
				drawable = getResources().getDrawable(R.drawable.biologie);
				  // Adjust the desired width
				 // Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button2.setCompoundDrawables(null, drawable, null, null);
				button2.setCompoundDrawablePadding(0);  // Adjust the desired padding

				
				button3.setText("Chimie\nBiochimie");
				drawable = getResources().getDrawable(R.drawable.chemistry);
				 // Adjust the desired width
		 // Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button3.setCompoundDrawables(null, drawable, null, null);
				button3.setCompoundDrawablePadding(0);  // Adjust the desired padding
				
				
				button4.setText("Communication et Langue");
				button4.setTextSize(10);
				drawable = getResources().getDrawable(R.drawable.communication);
				 // Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button4.setCompoundDrawables(null, drawable, null, null);
				button4.setCompoundDrawablePadding(0);  // Adjust the desired padding
				
				
				
				button5.setText("Methodo Apprentisage");
				drawable = getResources().getDrawable(R.drawable.apprentissage);
			 // Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button5.setCompoundDrawables(null, drawable, null, null);
				button5.setCompoundDrawablePadding(0);  // Adjust the desired padding
				
				
				button6.setText("Santé Publique");
				drawable = getResources().getDrawable(R.drawable.sante);
				// Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button6.setCompoundDrawables(null, drawable, null, null);
				button6.setCompoundDrawablePadding(0);  // Adjust the desired padding
				
				
				selectedSemester = "semester1";
				
			break;
			
			case "semester2":
				button1.setText("Anatomie II");
				drawable = getResources().getDrawable(R.drawable.anatomie);
				
				drawable.setBounds(0, 0, width, height);
				button1.setCompoundDrawables(null,drawable,null,null);
				//button4.setCompoundDrawablePadding(0);
				
				button2.setText("Histoire");
				button3.setText("Stage d'immersion");
				button4.setText("Histo\nEmbroyologie");
				drawable= getResources().getDrawable(R.drawable.histologie);
				// Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button4.setCompoundDrawables(null, drawable, null, null);
				button4.setCompoundDrawablePadding(0);  // Adjust the desired padding
				
				button5.setText("Biophysique");
				button6.setText("Tech. Communication");
				selectedSemester = "semester2";
				
			break;
			case "semester3":
				button1.setText("Anatomie III");
				drawable= getResources().getDrawable(R.drawable.anatomie);
				// Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button1.setCompoundDrawables(null, drawable, null, null);
				button1.setCompoundDrawablePadding(0);  // Adjust the desired padding
				
				
				button2.setText("Microbiologie");
				drawable = getResources().getDrawable(R.drawable.microbiologie);
				
				drawable.setBounds(0,0,width,height);
				button2.setCompoundDrawables(null,drawable,null,null);
				
				button3.setText("Physiologie I");
				drawable=getResources().getDrawable(R.drawable.physiologie);
				drawable.setBounds(0,0,width,height);
				button3.setCompoundDrawables(null,drawable,null,null);
				
				
				button4.setText("MedEx\nSecourisme");
				drawable=getResources().getDrawable(R.drawable.secourisme);
				drawable.setBounds(0,0,width,height);
				button4.setCompoundDrawables(null,drawable,null,null);
				
				button5.setText("Histo\nEmbryo");
				drawable= getResources().getDrawable(R.drawable.histologie);
				 // Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button5.setCompoundDrawables(null, drawable, null, null);
				
				
				button6.setText("Semiologie I");
				drawable= getResources().getDrawable(R.drawable.semio);
				 // Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button6.setCompoundDrawables(null, drawable, null, null);

				
				selectedSemester = "semester1";
				
				break;

			case "semester4":
				button1.setText("Anatomie IV");
				
				button2.setText("Hematologie");
				drawable = getResources().getDrawable(R.drawable.hemato);
				 // Adjust the desired height
				drawable.setBounds(0, 0, width, height);
				button2.setCompoundDrawables(null, drawable, null, null);
				
				
				
				button3.setText("Physiologie II");
				button4.setText("Biochimie Clinique");
				button5.setText("Stage d'Immersion");
				button6.setText("Semiologie II");
				selectedSemester = "semester2";
				
				break;
			case "semester5":
//				button1.setText();
//				button2.setText();
//				button3.setText();
//				button4.setText();
//				button5.setText();
//				button6.setText();
				break;

			case "semester6":
				
				break;
			case "semester7":
				break;

			case "semester8":
				break;
				default:
				break;
				
				
		}
		
		
		yearSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
					// your code here
					String selectedItem = years[position];
					Toast.makeText(ModuleActivity.this,"Selected year: " + selectedItem,Toast.LENGTH_LONG).show();
				
					selectedYear=selectedItem;
					
					}

				@Override
				public void onNothingSelected(AdapterView<?> parentView) {
					// your code here
				}

			});
			
			
			
		sessionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
				public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
					// your code here
					String selectedItem = sessions[position];
					Toast.makeText(ModuleActivity.this,"Selected session: " + selectedItem,Toast.LENGTH_LONG).show();
				
					if(selectedItem.equals("1e Session")){
					selectedSession = "session1";
					
					}else{
						selectedSession="session2";
					}
					
					}

				@Override
				public void onNothingSelected(AdapterView<?> parentView) {
					// your code here
				}

			});
			
			
		
		final LinearLayout linearLayout = findViewById(R.id.LinearLayout);
		
		
		final String[] button1Keys = {"anatomie","anatomie","anatomie","anatomie","methodoapprentisage","santepublique"};
		button1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				String module;
				String moduleName;
					switch(semester){
						case "semester1":
							module = button1Keys[0];
							moduleName="Anatomie I";
						openActivity(moduleName,module,year,selectedSemester);
						break;
					case "semester2":
						module = button1Keys[1];
						moduleName = "Anatomie II";
						openActivity(moduleName,module,year,selectedSemester);
						break;
					case "semester3":
						module = button1Keys[2];
						moduleName = "Anatomie III";
						openActivity(moduleName,module,year,selectedSemester);
						break;
					case "semester4":
						module = button1Keys[3];
						moduleName = "Anatomie IV";
						openActivity(moduleName,module,year,selectedSemester);
						break;
						default:
						break;
					}
					
				
				}
				
		});
		
		
		final String[] button2Keys = {"biologieGenetique","histoire","microbiologie","hematologie"};
		button2.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					
					String module;
					String moduleName;
					switch(semester){
						case "semester1":
							module = button2Keys[0];
							moduleName = "Biologie Cellulaire";
							openActivity(moduleName,module,year,semester);
							break;
							
						case "semester2":
							module = button2Keys[1];
							moduleName = "Histoire de la Médecine";
							openActivity(moduleName,module,year,semester);
							break;
						case "semester3":
							module = button2Keys[2];
							moduleName = "Microbiologie";
							openActivity(moduleName,module,year,semester);
							break;

						case "semester4":
							module = button2Keys[3];
							moduleName="Hematologie";
							openActivity(moduleName,module,year,semester);
							break;
							
							
							}
					
					
				}
			});
			
			
		final String[] button3Keys = {"chimie-Biochimie","stagedimmersion","physiologie1","physiologie2"};
		button3.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					String module;
					String moduleName;
					switch(semester){
						case "semester1":
							module = button3Keys[0];
							moduleName = "Chimie/Biochimie";
							openActivity(moduleName,module,year,semester);
							break;

						case "semester2":
							module = button3Keys[1];
							moduleName = "Stage d'Immersion";
							openActivity(moduleName,module,year,semester);
							break;
						case "semester3":
							module = button3Keys[2];
							moduleName = "Physiologie I";
							openActivity(moduleName,module,year,semester);
							break;

						case "semester4":
							module = button3Keys[3];
							moduleName = "Physiologie II";
							openActivity(moduleName,module,year,semester);
							break;


					}
				}
			});
			
			
		final String[] button4Keys = {"communicationLangue","histoEmbryologie","secourismeMedex","biochimieClinique"};
		button4.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					String module;
					
					switch(semester){
						case "semester1":
							module = button4Keys[0];
							String moduleName = "Communication et Langues";
							openActivity(moduleName,module,year,semester);
							break;

						case "semester2":
							module = button4Keys[1];
							moduleName = "Histologie/Embryologie";
							openActivity(moduleName,module,year,semester);
							break;
						case "semester3":
							module = button4Keys[2];
							moduleName ="Medex et Secourisme";
							openActivity(moduleName,module,year,semester);
							break;

						case "semester4":
							module = button4Keys[3];
							moduleName = "Biochimie Clinique";
							openActivity(moduleName,module,year,semester);
							break;


					}
				}
			});
			
		final String[] button5Keys = {"methodoApprentissage","biophysique","histoEmbryologie","stagedimmersion"};
		button5.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					String module;
					String moduleName;
					switch(semester){
						case "semester1":
							module = button5Keys[0];
							moduleName = "Methodologie d'Apprentisage";
							openActivity(moduleName,module,year,semester);
							break;

						case "semester2":
							module = button5Keys[1];
							moduleName = "Biophysique";
							openActivity(moduleName,module,year,semester);
							break;
						case "semester3":
							module = button5Keys[2];
							moduleName = "Histologie/Embryologie II";
							openActivity(moduleName,module,year,semester);
							break;

						case "semester4":
							module = button5Keys[3];
							moduleName="Stage d'Immersion";
							openActivity(moduleName,module,year,semester);
							break;


					}
				}
			});
			
			
		final String[] button6Keys = {"santepublique","communication","semiologie1","semiologie2"};
		button6.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					String module;
					String moduleName;
					
					switch(semester){
						case "semester1":
							module = button6Keys[0];
							moduleName = "Santé Publique";
							openActivity(moduleName, module,year,semester);
							break;

						case "semester2":
							module = button6Keys[1];
							moduleName = "Techniques de Communication";
							openActivity(moduleName,module,year,semester);
							break;
						case "semester3":
							module = button6Keys[2];
							moduleName = "Semiologie II";
							openActivity(moduleName,module,year,semester);
							break;

						case "semester4":
							module = button6Keys[3];
						moduleName ="Semiologie II";
							openActivity(moduleName,module,year,semester);
							break;


					}
				}
			});
			
		final float screenWidth = size.x;
		final float screenHeight = size.y;
		
		ViewTreeObserver viewTreeObserver = button1.getViewTreeObserver();
		viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
				@Override
				public void onGlobalLayout() {
					button1.getViewTreeObserver().removeOnGlobalLayoutListener(this);

					float buttonWidth = button1.getWidth();
					float buttonHeight = button1.getHeight();

					float centerX = (screenWidth-buttonWidth)/  2;
					float centerY = (screenHeight-buttonWidth) /  2;

					float radius = 350f;

					int buttonCount = 6; // The number of buttons you want to position
					float angle = 360f / buttonCount;
					final Button[] buttons = {button1, button2, button3, button4, button5, button6};
					for (int index = 0; index < buttonCount; index++) {
						final int i;
						i=index;
						
						double radians = Math.toRadians((i * angle)+29.3); 
						
						// Calculate the button position
						int buttonX = (int) (centerX + radius * Math.cos(radians));
						int buttonY = (int) (centerY + radius * Math.sin(radians));

						// Create and position the button
						buttons[i].setX(buttonX - buttonWidth / 2);
						buttons[i].setY(buttonY - buttonHeight / 2);

						// Add the button to your layout
						//linearLayout.addView(buttons[i]);

						// Create a Path object for each button with adjusted starting angle
					final	Path path = new Path();
						path.addArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius,
									(float) Math.toDegrees(radians), 360f);
						
									
						ObjectAnimator fastRotation = ObjectAnimator.ofFloat(buttons[i], View.X, View.Y, path);
						fastRotation.setInterpolator(new DecelerateInterpolator());
						fastRotation.setDuration(1200);
						
						//slow rotation below
						
//						fastRotation.addListener(new AnimatorListenerAdapter() {
//								@Override
//								public void onAnimationEnd(Animator animation) {
//									// Animation ended, perform your desired actions here
//									// This method will be called when the animation is finished
//									ObjectAnimator animator = ObjectAnimator.ofFloat(buttons[i], View.X, View.Y, path);
//									animator.setDuration(10000);
//									animator.setRepeatCount(Animation.INFINITE);
//									animator.start();
//								}
//							});
							fastRotation.start();
						// Create an ObjectAnimator for each button
						
					}
				}
			});
			
			
	}
	public void openActivity(String moduleName,String module,String year,String semester){
		Intent intent = new Intent(this,MainActivity.class);
	
		intent.putExtra("selectedYear", selectedYear);
		intent.putExtra("selectedSession", selectedSession);
		intent.putExtra("modulekey",module);
		intent.putExtra("modulename",moduleName);
		intent.putExtra("year",year);
		intent.putExtra("semester",selectedSemester);
		startActivity(intent);
		
	}
	
	public void maker(){
		Toast.makeText(this,selectedSession,Toast.LENGTH_SHORT).show();
		
	}
	
	
	}
