// Generated by view binder compiler. Do not edit!
package com.example.Munasabati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public final class ActivityForgotPassBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView TextViewResend;

  @NonNull
  public final Button buttonLogin2;

  @NonNull
  public final EditText editTextLoginUser2;

  @NonNull
  public final ImageView imageView27;

  @NonNull
  public final ImageView imageView33;

  @NonNull
  public final TextView imageView34;

  @NonNull
  public final TextView imageView35;

  @NonNull
  public final ImageView imageViewDontHave2;

  private ActivityForgotPassBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView TextViewResend, @NonNull Button buttonLogin2,
      @NonNull EditText editTextLoginUser2, @NonNull ImageView imageView27,
      @NonNull ImageView imageView33, @NonNull TextView imageView34, @NonNull TextView imageView35,
      @NonNull ImageView imageViewDontHave2) {
    this.rootView = rootView;
    this.TextViewResend = TextViewResend;
    this.buttonLogin2 = buttonLogin2;
    this.editTextLoginUser2 = editTextLoginUser2;
    this.imageView27 = imageView27;
    this.imageView33 = imageView33;
    this.imageView34 = imageView34;
    this.imageView35 = imageView35;
    this.imageViewDontHave2 = imageViewDontHave2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityForgotPassBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityForgotPassBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_forgot_pass, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityForgotPassBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.TextViewResend;
      TextView TextViewResend = ViewBindings.findChildViewById(rootView, id);
      if (TextViewResend == null) {
        break missingId;
      }

      id = R.id.buttonLogin2;
      Button buttonLogin2 = ViewBindings.findChildViewById(rootView, id);
      if (buttonLogin2 == null) {
        break missingId;
      }

      id = R.id.editTextLoginUser2;
      EditText editTextLoginUser2 = ViewBindings.findChildViewById(rootView, id);
      if (editTextLoginUser2 == null) {
        break missingId;
      }

      id = R.id.imageView27;
      ImageView imageView27 = ViewBindings.findChildViewById(rootView, id);
      if (imageView27 == null) {
        break missingId;
      }

      id = R.id.imageView33;
      ImageView imageView33 = ViewBindings.findChildViewById(rootView, id);
      if (imageView33 == null) {
        break missingId;
      }

      id = R.id.imageView34;
      TextView imageView34 = ViewBindings.findChildViewById(rootView, id);
      if (imageView34 == null) {
        break missingId;
      }

      id = R.id.imageView35;
      TextView imageView35 = ViewBindings.findChildViewById(rootView, id);
      if (imageView35 == null) {
        break missingId;
      }

      id = R.id.imageViewDontHave2;
      ImageView imageViewDontHave2 = ViewBindings.findChildViewById(rootView, id);
      if (imageViewDontHave2 == null) {
        break missingId;
      }

      return new ActivityForgotPassBinding((ConstraintLayout) rootView, TextViewResend,
          buttonLogin2, editTextLoginUser2, imageView27, imageView33, imageView34, imageView35,
          imageViewDontHave2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
