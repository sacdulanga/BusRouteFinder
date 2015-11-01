package com.example.busroutefinder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

@SuppressLint("ValidFragment")
public class MyDialog2 extends DialogFragment{
	
	String message,title;
	
	public MyDialog2(String message,String title){
		this.message=message;
		this.title=title;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		builder.setTitle(title);
		builder.setIcon(R.drawable.icon);
		builder.setMessage(message);
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		Dialog dialog=builder.create();
		return dialog;
	}
	
}
