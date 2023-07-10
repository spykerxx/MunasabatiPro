// Generated by view binder compiler. Do not edit!
package com.example.Munasabati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.Munasabati.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LayoutBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final CardView cardView;

  @NonNull
  public final ImageView ivEventimage;

  @NonNull
  public final Spinner spinnerAdmin;

  @NonNull
  public final TextView tvEventDate;

  @NonNull
  public final TextView tvEventLocation;

  @NonNull
  public final TextView tvListName;

  @NonNull
  public final TextView tvServiceName;

  private LayoutBinding(@NonNull CardView rootView, @NonNull CardView cardView,
      @NonNull ImageView ivEventimage, @NonNull Spinner spinnerAdmin, @NonNull TextView tvEventDate,
      @NonNull TextView tvEventLocation, @NonNull TextView tvListName,
      @NonNull TextView tvServiceName) {
    this.rootView = rootView;
    this.cardView = cardView;
    this.ivEventimage = ivEventimage;
    this.spinnerAdmin = spinnerAdmin;
    this.tvEventDate = tvEventDate;
    this.tvEventLocation = tvEventLocation;
    this.tvListName = tvListName;
    this.tvServiceName = tvServiceName;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static LayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LayoutBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      CardView cardView = (CardView) rootView;

      id = R.id.iv_eventimage;
      ImageView ivEventimage = ViewBindings.findChildViewById(rootView, id);
      if (ivEventimage == null) {
        break missingId;
      }

      id = R.id.spinnerAdmin;
      Spinner spinnerAdmin = ViewBindings.findChildViewById(rootView, id);
      if (spinnerAdmin == null) {
        break missingId;
      }

      id = R.id.tv_eventDate;
      TextView tvEventDate = ViewBindings.findChildViewById(rootView, id);
      if (tvEventDate == null) {
        break missingId;
      }

      id = R.id.tv_eventLocation;
      TextView tvEventLocation = ViewBindings.findChildViewById(rootView, id);
      if (tvEventLocation == null) {
        break missingId;
      }

      id = R.id.tv_listName;
      TextView tvListName = ViewBindings.findChildViewById(rootView, id);
      if (tvListName == null) {
        break missingId;
      }

      id = R.id.tv_ServiceName;
      TextView tvServiceName = ViewBindings.findChildViewById(rootView, id);
      if (tvServiceName == null) {
        break missingId;
      }

      return new LayoutBinding((CardView) rootView, cardView, ivEventimage, spinnerAdmin,
          tvEventDate, tvEventLocation, tvListName, tvServiceName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}