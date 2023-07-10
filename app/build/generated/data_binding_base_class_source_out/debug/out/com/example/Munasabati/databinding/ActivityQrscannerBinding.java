// Generated by view binder compiler. Do not edit!
package com.example.Munasabati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.Munasabati.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityQrscannerBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonScanQR;

  @NonNull
  public final ImageView sideButtonQR;

  @NonNull
  public final Spinner spinnerEventName;

  @NonNull
  public final TextView textView44;

  @NonNull
  public final TextView textViewEventNameQR;

  @NonNull
  public final TextView textViewQrResult;

  @NonNull
  public final View view12;

  private ActivityQrscannerBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonScanQR,
      @NonNull ImageView sideButtonQR, @NonNull Spinner spinnerEventName,
      @NonNull TextView textView44, @NonNull TextView textViewEventNameQR,
      @NonNull TextView textViewQrResult, @NonNull View view12) {
    this.rootView = rootView;
    this.buttonScanQR = buttonScanQR;
    this.sideButtonQR = sideButtonQR;
    this.spinnerEventName = spinnerEventName;
    this.textView44 = textView44;
    this.textViewEventNameQR = textViewEventNameQR;
    this.textViewQrResult = textViewQrResult;
    this.view12 = view12;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityQrscannerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityQrscannerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_qrscanner, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityQrscannerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonScanQR;
      Button buttonScanQR = ViewBindings.findChildViewById(rootView, id);
      if (buttonScanQR == null) {
        break missingId;
      }

      id = R.id.sideButtonQR;
      ImageView sideButtonQR = ViewBindings.findChildViewById(rootView, id);
      if (sideButtonQR == null) {
        break missingId;
      }

      id = R.id.spinnerEventName;
      Spinner spinnerEventName = ViewBindings.findChildViewById(rootView, id);
      if (spinnerEventName == null) {
        break missingId;
      }

      id = R.id.textView44;
      TextView textView44 = ViewBindings.findChildViewById(rootView, id);
      if (textView44 == null) {
        break missingId;
      }

      id = R.id.textViewEventNameQR;
      TextView textViewEventNameQR = ViewBindings.findChildViewById(rootView, id);
      if (textViewEventNameQR == null) {
        break missingId;
      }

      id = R.id.textViewQrResult;
      TextView textViewQrResult = ViewBindings.findChildViewById(rootView, id);
      if (textViewQrResult == null) {
        break missingId;
      }

      id = R.id.view12;
      View view12 = ViewBindings.findChildViewById(rootView, id);
      if (view12 == null) {
        break missingId;
      }

      return new ActivityQrscannerBinding((ConstraintLayout) rootView, buttonScanQR, sideButtonQR,
          spinnerEventName, textView44, textViewEventNameQR, textViewQrResult, view12);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
