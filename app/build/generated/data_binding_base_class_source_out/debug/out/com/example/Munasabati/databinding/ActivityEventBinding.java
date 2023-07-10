// Generated by view binder compiler. Do not edit!
package com.example.Munasabati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public final class ActivityEventBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonCheckIn;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final ImageView imageView6;

  @NonNull
  public final ImageView imageViewRoom;

  @NonNull
  public final TextView textViewDate;

  @NonNull
  public final TextView textViewEventName;

  @NonNull
  public final TextView textViewGetDirections;

  @NonNull
  public final TextView textViewLocation;

  @NonNull
  public final TextView textViewRoomNumberMain2;

  @NonNull
  public final TextView textViewRoomNumberMain3;

  @NonNull
  public final TextView textViewTime;

  @NonNull
  public final View view2;

  @NonNull
  public final View view4;

  @NonNull
  public final View viewDark;

  private ActivityEventBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonCheckIn,
      @NonNull ImageView imageView3, @NonNull ImageView imageView6,
      @NonNull ImageView imageViewRoom, @NonNull TextView textViewDate,
      @NonNull TextView textViewEventName, @NonNull TextView textViewGetDirections,
      @NonNull TextView textViewLocation, @NonNull TextView textViewRoomNumberMain2,
      @NonNull TextView textViewRoomNumberMain3, @NonNull TextView textViewTime,
      @NonNull View view2, @NonNull View view4, @NonNull View viewDark) {
    this.rootView = rootView;
    this.buttonCheckIn = buttonCheckIn;
    this.imageView3 = imageView3;
    this.imageView6 = imageView6;
    this.imageViewRoom = imageViewRoom;
    this.textViewDate = textViewDate;
    this.textViewEventName = textViewEventName;
    this.textViewGetDirections = textViewGetDirections;
    this.textViewLocation = textViewLocation;
    this.textViewRoomNumberMain2 = textViewRoomNumberMain2;
    this.textViewRoomNumberMain3 = textViewRoomNumberMain3;
    this.textViewTime = textViewTime;
    this.view2 = view2;
    this.view4 = view4;
    this.viewDark = viewDark;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEventBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEventBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_event, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEventBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonCheckIn;
      Button buttonCheckIn = ViewBindings.findChildViewById(rootView, id);
      if (buttonCheckIn == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.imageView6;
      ImageView imageView6 = ViewBindings.findChildViewById(rootView, id);
      if (imageView6 == null) {
        break missingId;
      }

      id = R.id.imageViewRoom;
      ImageView imageViewRoom = ViewBindings.findChildViewById(rootView, id);
      if (imageViewRoom == null) {
        break missingId;
      }

      id = R.id.textViewDate;
      TextView textViewDate = ViewBindings.findChildViewById(rootView, id);
      if (textViewDate == null) {
        break missingId;
      }

      id = R.id.textViewEventName;
      TextView textViewEventName = ViewBindings.findChildViewById(rootView, id);
      if (textViewEventName == null) {
        break missingId;
      }

      id = R.id.textViewGetDirections;
      TextView textViewGetDirections = ViewBindings.findChildViewById(rootView, id);
      if (textViewGetDirections == null) {
        break missingId;
      }

      id = R.id.textViewLocation;
      TextView textViewLocation = ViewBindings.findChildViewById(rootView, id);
      if (textViewLocation == null) {
        break missingId;
      }

      id = R.id.textViewRoomNumberMain2;
      TextView textViewRoomNumberMain2 = ViewBindings.findChildViewById(rootView, id);
      if (textViewRoomNumberMain2 == null) {
        break missingId;
      }

      id = R.id.textViewRoomNumberMain3;
      TextView textViewRoomNumberMain3 = ViewBindings.findChildViewById(rootView, id);
      if (textViewRoomNumberMain3 == null) {
        break missingId;
      }

      id = R.id.textViewTime;
      TextView textViewTime = ViewBindings.findChildViewById(rootView, id);
      if (textViewTime == null) {
        break missingId;
      }

      id = R.id.view2;
      View view2 = ViewBindings.findChildViewById(rootView, id);
      if (view2 == null) {
        break missingId;
      }

      id = R.id.view4;
      View view4 = ViewBindings.findChildViewById(rootView, id);
      if (view4 == null) {
        break missingId;
      }

      id = R.id.viewDark;
      View viewDark = ViewBindings.findChildViewById(rootView, id);
      if (viewDark == null) {
        break missingId;
      }

      return new ActivityEventBinding((ConstraintLayout) rootView, buttonCheckIn, imageView3,
          imageView6, imageViewRoom, textViewDate, textViewEventName, textViewGetDirections,
          textViewLocation, textViewRoomNumberMain2, textViewRoomNumberMain3, textViewTime, view2,
          view4, viewDark);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}