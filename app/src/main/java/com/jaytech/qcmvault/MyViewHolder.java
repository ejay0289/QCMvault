package com.jaytech.qcmvault;
import android.annotation.NonNull;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.cardview.widget.CardView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    
	ImageView imageView;
	TextView nameView,emailView;
	TextView question;
	CheckBox answerA, answerB,answerC,answerD,answerE;
	TextView name;
	Button correctButton,retryButton;
	CardView card;
	TextView optionError;
    public MyViewHolder(@NonNull View itemView){
		super(itemView);
		question = itemView.findViewById(R.id.questionText);
		answerA = itemView.findViewById(R.id.answerA);
		answerB = itemView.findViewById(R.id.answerB);
		answerC = itemView.findViewById(R.id.answerC);
		answerD = itemView.findViewById(R.id.answerD);
		answerE  = itemView.findViewById(R.id.answerE);
		optionError = itemView.findViewById(R.id.optionError);
		card = itemView.findViewById(R.id.card);
		
		
		
		//final QuestionsAnswers questionsAnswers = new QuestionsAnswers();
		
		correctButton = itemView.findViewById(R.id.correctButton);
		retryButton = itemView.findViewById(R.id.retryButton);
		
		
		
		
	}
    
}
