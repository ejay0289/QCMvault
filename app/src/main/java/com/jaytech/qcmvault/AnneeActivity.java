package com.jaytech.qcmvault;
//app:layout_constraintHeight_percent="0.75"
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.widget.TextView;


public class AnneeActivity extends Activity {
    FirebaseAuth auth;
	public final static String extraYear = "year";
	public static final String extraSemester = "semester";
    CardView firstyear;
	Window window;
    ConstraintLayout rectView;
	ImageView arrow1;
	ConstraintLayout innerLayout,outerLayout;
	ImageView waveView;
    View navDrawer;
    ImageView burgerMenu;
    Button logout;
	private boolean isNavigationDrawerVisible = false;
    View overlay;
TextView bonjour;
	@Override
	protected void onResume() {
		super.onResume();

		waveView = findViewById(R.id.wave);
		wave(waveView); // Call the wave method with your ImageView reference
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annee_activity);

		//overflow into statusbar
		getWindow().setFlags(
			WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
			WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
		);
		rectView = findViewById(R.id.rectView);
        navDrawer = findViewById(R.id.navigation_drawer);
        burgerMenu = findViewById(R.id.burgermenu);
        overlay = findViewById(R.id.overlay_view);
		logout = findViewById(R.id.logout);

        navDrawer.setTranslationZ(10f); // Move the navigation drawer to a higher position
        overlay.setTranslationZ(5f); // Move the overlay to a lower position
        rectView.setTranslationZ(0f);
      
        firstyear = findViewById(R.id.firstyear);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		arrow1 = findViewById(R.id.firstyeararrow);
		LinearLayout firstBackground = findViewById(R.id.firstyearbackground);
        LinearLayout secondBackground = findViewById(R.id.secondyearbackground);
        LinearLayout thirdBackground = findViewById(R.id.thirdyearbackground);
        LinearLayout fourthBackground = findViewById(R.id.fourthyearbackground);
		waveView = findViewById(R.id.wave);
		wave(waveView);
        setUserName();
		ViewTreeObserver viewTreeObserver = waveView.getViewTreeObserver();
		viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
				@Override
				public void onGlobalLayout() {
					// Remove the listener to prevent it from being called again
					waveView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

					// Call the wave method when the layout is measured
					wave(waveView);
				}
			});
//        AnimationDrawable animationFirst = (AnimationDrawable) firstBackground.getBackground();
//        
//        animationSixth.setEnterFadeDuration(5);
//        animationSixth.setExitFadeDuration(2000);
//        animationSixth.start();

        burgerMenu.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (isNavigationDrawerVisible) {
                        hideNavigationDrawer();
                    } else {
                        showNavigationDrawer();
                    }
                }
            });

        overlay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    hideNavigationDrawer();
                }
            });
        logout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    logout();

                }
            });
            
		firstBackground.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Context context = new ContextThemeWrapper(AnneeActivity.this, R.style.PopupMenu);
					rotate90deg(arrow1);
					PopupMenu popUp = new PopupMenu(context, v);
					MenuInflater inflater = popUp.getMenuInflater();
					inflater.inflate(R.menu.popupone, popUp.getMenu());


					popUp.setOnDismissListener(new PopupMenu.OnDismissListener() {
							@Override
							public void onDismiss(PopupMenu menu) {
								// Play the animation when the popup menu is dismissed

								rotateNeg90deg(arrow1);
							}
						});

					popUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
							@Override
							public boolean onMenuItemClick(MenuItem item) {
								// Handle menu item clicks here
								Intent intent = new Intent(AnneeActivity.this, ModuleActivity.class);
								switch (item.getItemId()) {
									case R.id.s1:
										// Action for menu item 1
										intent.putExtra(extraYear, "year1");
										intent.putExtra("extraAnnee", "1ᵉʳ Année");
										intent.putExtra(extraSemester, "semester1");
										startActivity(intent);
										return true;
									case R.id.s2:
										// Action for menu item 2
										intent.putExtra(extraYear, "year1");
										intent.putExtra("extraAnnee", "1ᵉʳ Année");
										intent.putExtra(extraSemester, "semester2");

										startActivity(intent);
										return true;
										// Add more cases for other menu items as needed
									default:
										return false;
								}
							}
						});

					// Show the popup menu
					popUp.show();
				}
			});

		secondBackground.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					PopupMenu popUp = new PopupMenu(AnneeActivity.this, v);
					MenuInflater inflater = popUp.getMenuInflater();
					inflater.inflate(R.menu.popuptwo, popUp.getMenu());




					popUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
							@Override
							public boolean onMenuItemClick(MenuItem item) {
								// Handle menu item clicks here
								Intent intent = new Intent(AnneeActivity.this, ModuleActivity.class);
								switch (item.getItemId()) {
									case R.id.s3:
										// Action for menu item 1
										intent.putExtra(extraYear, "year2");
										intent.putExtra("extraAnnee", "2ᵉᵐᵉ Année");
										//+ "ᵉ\u0300ᵐᵉ"
										intent.putExtra(extraSemester, "semester3");
										startActivity(intent);
										return true;
									case R.id.s4:
										// Action for menu item 2

										intent.putExtra(extraYear, "year2");
										intent.putExtra("extraAnnee", "2ᵉᵐᵉ Année");
										intent.putExtra(extraSemester, "semester4");

										startActivity(intent);
										return true;
										// Add more cases for other menu items as needed
									default:
										return false;
								}
							}
						});

					// Show the popup menu
					popUp.show();
				}
			});

		thirdBackground.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					PopupMenu popUp = new PopupMenu(AnneeActivity.this, v, Gravity.TOP);
					MenuInflater inflater = popUp.getMenuInflater();
					inflater.inflate(R.menu.popupthree, popUp.getMenu());




					popUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
							@Override
							public boolean onMenuItemClick(MenuItem item) {
								// Handle menu item clicks here
								Intent intent = new Intent(AnneeActivity.this, ModuleActivity.class);
								switch (item.getItemId()) {
									case R.id.s5:
										// Action for menu item 1
										intent.putExtra(extraYear, "year3");
										intent.putExtra("extraAnnee", "3ᵉᵐᵉ Année");
										intent.putExtra(extraSemester, "semester5");
										startActivity(intent);
										return true;
									case R.id.s6:
										// Action for menu item 2


										intent.putExtra(extraYear, "year3");
										intent.putExtra("extraAnnee", "3ᵉᵐᵉ Année");
										intent.putExtra(extraSemester, "semester6");

										startActivity(intent);
										return true;
										// Add more cases for other menu items as needed
									default:
										return false;
								}
							}
						});

					// Show the popup menu
					popUp.show();
				}
			});

		fourthBackground.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					PopupMenu popUp = new PopupMenu(AnneeActivity.this, v);
					MenuInflater inflater = popUp.getMenuInflater();
					inflater.inflate(R.menu.popupfour, popUp.getMenu());


					popUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
							@Override
							public boolean onMenuItemClick(MenuItem item) {
								// Handle menu item clicks here
								Intent intent = new Intent(AnneeActivity.this, ModuleActivity.class);
								switch (item.getItemId()) {
									case R.id.s7:
										// Action for menu item 1
										intent.putExtra(extraYear, "year4");
										intent.putExtra("extraAnnee", "4ᵉᵐᵉ Année");
										intent.putExtra(extraSemester, "semester7");
										startActivity(intent);
										return true;
									case R.id.s8:
										// Action for menu item 2
										intent.putExtra(extraYear, "year4");
										intent.putExtra("extraAnnee", "4ᵉᵐᵉ Année");
										intent.putExtra(extraSemester, "semester8");

										startActivity(intent);
										return true;
										// Add more cases for other menu items as needed
									default:
										return false;
								}
							}
						});

					// Show the popup menu
					popUp.show();
				}
			});



    }


    public void rotate90deg(View v) {

		PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat(View.ROTATION, 0f, 90f);

		// Create an ObjectAnimator with rotationHolder
		ObjectAnimator rotationAnimator = ObjectAnimator.ofPropertyValuesHolder(v, rotationHolder);

		// Set the duration of the animation
		rotationAnimator.setDuration(500); // 1 second
		rotationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
		// Start the animation
		rotationAnimator.start();
    }

	public void rotateNeg90deg(View v) {
		PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat(View.ROTATION, 90f, 0f);
		ObjectAnimator rotationAnimator = ObjectAnimator.ofPropertyValuesHolder(v, rotationHolder);
		rotationAnimator.setDuration(500);
		rotationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
		rotationAnimator.start();
	}

	public void wave(final View v) {

        // Get the final width and height of the view
        final int width = v.getWidth();
        final int height = v.getHeight();

        final int WAVE_DURATION = 300;

        final ObjectAnimator wave = ObjectAnimator.ofFloat(v, "rotation", 0, 45);

        // Set the pivot point to the bottom right corner
        v.setPivotX(width);
        v.setPivotY(height);

        wave.setDuration(WAVE_DURATION);
        wave.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    // Animation start logic if needed
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ObjectAnimator antiwave = ObjectAnimator.ofFloat(v, "rotation", 45, 0);
                    antiwave.setDuration(WAVE_DURATION);
                    antiwave.start();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    // Animation cancel logic if needed
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    // Animation repeat logic if needed
                }
            });

        wave.start();
    }

    private void showNavigationDrawer() {
        isNavigationDrawerVisible = true;
        navDrawer.setVisibility(View.VISIBLE);
        overlay.setVisibility(View.VISIBLE);
        // Create ObjectAnimator for slide-in animation
        ObjectAnimator animator = ObjectAnimator.ofFloat(navDrawer, "translationX", -navDrawer.getWidth(), 0);
        animator.setDuration(300); // Set the duration of the animation in milliseconds
        animator.start();
    }

	private void hideNavigationDrawer() {
        isNavigationDrawerVisible = false;

        // Create ObjectAnimator for slide-out animation
        ObjectAnimator animator = ObjectAnimator.ofFloat(navDrawer, "translationX", 0, -navDrawer.getWidth());
        animator.setDuration(300); // Set the duration of the animation in milliseconds
        animator.start();

        // Set a delay to hide the navigation drawer after the animation completes
        navDrawer.postDelayed(new Runnable() {
                @Override
                public void run() {
                    navDrawer.setVisibility(View.GONE);
                    overlay.setVisibility(View.GONE);
                }
            }, 300); // Delay should be the same as the animation duration
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(AnneeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void setUserName(){
        bonjour = findViewById(R.id.bonjour);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String username = user.getDisplayName();
        bonjour.setText("Bonjour Dr. " + username);
    }
}

