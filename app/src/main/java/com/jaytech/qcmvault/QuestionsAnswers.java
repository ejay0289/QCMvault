package com.jaytech.qcmvault;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;



public class QuestionsAnswers
{
	
	
	String[] questions = {"1.) En ce qui concerne le méningocoque :",
		"2.) En ce qui concerne l'Haemophilus influenzae :","3.) En ce qui concerne corynebactérium diphtériae:"};
		
	String[][] Answers = {
		{"A- C'est un diplocoque à Gram négatif",
		"B- Il est responsable de méningites purulentes",
		"C- Il peut être de portage rhinopharyngé",
		"D- L'infection associée peut être prévenue par vaccination",
		"E- Il est détecté par diagnostic direct"},
		
		{"A- C'est un bacille à Gram négatif",
			"B- Il peut être responsable de méningite",
			"C- Il existe sous forme d'un seul sérotype",
			"D- C'est un germe de culture facile au laboratoire",
			"E- Le vaccin anti Hib (Haemophilus influenzae b) fait partie du PNI"},
		{"A- C'est un bacille à Gram négatif",
		"B-Il peut être isolé d'une hémoculture",
			"C- C'est une bactérie toxinogène",
			"D- C'est un Parasite strict de l'homme",
			"E- L'infection associée à ce germe peut être prévenue par la vaccination"}
		};
	
		
		boolean[][] correctAnswers = {
			{true,true,true,true,true},
		{true,true,false,false,true},
		{false,false,true,true,true}};
	
	
	
	
	
}
