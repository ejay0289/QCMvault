package com.jaytech.qcmvault;
import android.annotation.NonNull;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = items.get(position);

        holder.question.setText(item.getQuestion());
        holder.answerA.setText(item.getAnswerOptions().get(0));
        holder.answerB.setText(item.getAnswerOptions().get(1));
        holder.answerC.setText(item.getAnswerOptions().get(2));
        holder.answerD.setText(item.getAnswerOptions().get(3));
        holder.answerE.setText(item.getAnswerOptions().get(4));

        final List<Boolean> correctAnswers = item.getCorrectAnswers();
       final CheckBox[] checkboxes = {holder.answerA, holder.answerB, holder.answerC, holder.answerD, holder.answerE};
//holder.card.setBackground(Color.WHITE);
        holder.correctButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					
					for (int i = 0; i < checkboxes.length; i++) {
						CheckBox checkbox = checkboxes[i];
						boolean isCorrect = correctAnswers.get(i);
						if(holder.answerA.isChecked()||holder.answerB.isChecked()||holder.answerC.isChecked()||holder.answerD.isChecked()||holder.answerE.isChecked()){
							if (isCorrect) {
								checkbox.setTextColor(Color.GREEN);
								checkbox.setBackgroundResource(R.drawable.card_background);
								holder.optionError.setVisibility(View.GONE);
							} else {
								checkbox.setTextColor(Color.RED);
							}
						}else{
							holder.optionError.setVisibility(View.VISIBLE);
						}
						
					}
				}
			});
			
		
		
			
			holder.retryButton.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					for (int i = 0; i < checkboxes.length; i++) {
						CheckBox checkbox = checkboxes[i];
						checkbox.setTextColor(Color.BLACK);
						checkbox.setChecked(false);
						
						}
					holder.optionError.setVisibility(View.GONE);
				}
			});

        // TODO: Add click listener for retry button if needed
    }
	@Override
	public int getItemCount() {
		return items.size();
	}
	
}

