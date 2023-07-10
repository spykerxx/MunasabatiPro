// Generated by view binder compiler. Do not edit!
package com.example.Munasabati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.Munasabati.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignupBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonSignUp;

  @NonNull
  public final EditText editTextEmail;

  @NonNull
  public final EditText editTextSignupPass;

  @NonNull
  public final EditText editTextSignupPassConf;

  @NonNull
  public final EditText editTextTextPersonName;

  @NonNull
  public final EditText editTextTextPhone;

  @NonNull
  public final ImageView imageView11;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final ImageView imageView5;

  @NonNull
  public final ImageView imageViewAlreadyHave;

  @NonNull
  public final ImageView imageViewS22;

  @NonNull
  public final ImageView imageViewS33;

  @NonNull
  public final ImageView imageViewS44;

  @NonNull
  public final ImageView imageViewS55;

  @NonNull
  public final ScrollView scrollView2;

  @NonNull
  public final Spinner spinnerSignup;

  @NonNull
  public final ConstraintLayout viewTest1;

  @NonNull
  public final ConstraintLayout viewTest2;

  private ActivitySignupBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonSignUp,
      @NonNull EditText editTextEmail, @NonNull EditText editTextSignupPass,
      @NonNull EditText editTextSignupPassConf, @NonNull EditText editTextTextPersonName,
      @NonNull EditText editTextTextPhone, @NonNull ImageView imageView11,
      @NonNull ImageView imageView2, @NonNull ImageView imageView5,
      @NonNull ImageView imageViewAlreadyHave, @NonNull ImageView imageViewS22,
      @NonNull ImageView imageViewS33, @NonNull ImageView imageViewS44,
      @NonNull ImageView imageViewS55, @NonNull ScrollView scrollView2,
      @NonNull Spinner spinnerSignup, @NonNull ConstraintLayout viewTest1,
      @NonNull ConstraintLayout viewTest2) {
    this.rootView = rootView;
    this.buttonSignUp = buttonSignUp;
    this.editTextEmail = editTextEmail;
    this.editTextSignupPass = editTextSignupPass;
    this.editTextSignupPassConf = editTextSignupPassConf;
    this.editTextTextPersonName = editTextTextPersonName;
    this.editTextTextPhone = editTextTextPhone;
    this.imageView11 = imageView11;
    this.imageView2 = imageView2;
    this.imageView5 = imageView5;
    this.imageViewAlreadyHave = imageViewAlreadyHave;
    this.imageViewS22 = imageViewS22;
    this.imageViewS33 = imageViewS33;
    this.imageViewS44 = imageViewS44;
    this.imageViewS55 = imageViewS55;
    this.scrollView2 = scrollView2;
    this.spinnerSignup = spinnerSignup;
    this.viewTest1 = viewTest1;
    this.viewTest2 = viewTest2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_signup, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignupBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonSignUp;
      Button buttonSignUp = ViewBindings.findChildViewById(rootView, id);
      if (buttonSignUp == null) {
        break missingId;
      }

      id = R.id.editTextEmail;
      EditText editTextEmail = ViewBindings.findChildViewById(rootView, id);
      if (editTextEmail == null) {
        break missingId;
      }

      id = R.id.editTextSignupPass;
      EditText editTextSignupPass = ViewBindings.findChildViewById(rootView, id);
      if (editTextSignupPass == null) {
        break missingId;
      }

      id = R.id.editTextSignupPassConf;
      EditText editTextSignupPassConf = ViewBindings.findChildViewById(rootView, id);
      if (editTextSignupPassConf == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName;
      EditText editTextTextPersonName = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName == null) {
        break missingId;
      }

      id = R.id.editTextTextPhone;
      EditText editTextTextPhone = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPhone == null) {
        break missingId;
      }

      id = R.id.imageView11;
      ImageView imageView11 = ViewBindings.findChildViewById(rootView, id);
      if (imageView11 == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.imageView5;
      ImageView imageView5 = ViewBindings.findChildViewById(rootView, id);
      if (imageView5 == null) {
        break missingId;
      }

      id = R.id.imageViewAlreadyHave;
      ImageView imageViewAlreadyHave = ViewBindings.findChildViewById(rootView, id);
      if (imageViewAlreadyHave == null) {
        break missingId;
      }

      id = R.id.imageViewS22;
      ImageView imageViewS22 = ViewBindings.findChildViewById(rootView, id);
      if (imageViewS22 == null) {
        break missingId;
      }

      id = R.id.imageViewS33;
      ImageView imageViewS33 = ViewBindings.findChildViewById(rootView, id);
      if (imageViewS33 == null) {
        break missingId;
      }

      id = R.id.imageViewS44;
      ImageView imageViewS44 = ViewBindings.findChildViewById(rootView, id);
      if (imageViewS44 == null) {
        break missingId;
      }

      id = R.id.imageViewS55;
      ImageView imageViewS55 = ViewBindings.findChildViewById(rootView, id);
      if (imageViewS55 == null) {
        break missingId;
      }

      id = R.id.scrollView2;
      ScrollView scrollView2 = ViewBindings.findChildViewById(rootView, id);
      if (scrollView2 == null) {
        break missingId;
      }

      id = R.id.spinnerSignup;
      Spinner spinnerSignup = ViewBindings.findChildViewById(rootView, id);
      if (spinnerSignup == null) {
        break missingId;
      }

      id = R.id.viewTest1;
      ConstraintLayout viewTest1 = ViewBindings.findChildViewById(rootView, id);
      if (viewTest1 == null) {
        break missingId;
      }

      id = R.id.viewTest2;
      ConstraintLayout viewTest2 = ViewBindings.findChildViewById(rootView, id);
      if (viewTest2 == null) {
        break missingId;
      }

      return new ActivitySignupBinding((ConstraintLayout) rootView, buttonSignUp, editTextEmail,
          editTextSignupPass, editTextSignupPassConf, editTextTextPersonName, editTextTextPhone,
          imageView11, imageView2, imageView5, imageViewAlreadyHave, imageViewS22, imageViewS33,
          imageViewS44, imageViewS55, scrollView2, spinnerSignup, viewTest1, viewTest2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
