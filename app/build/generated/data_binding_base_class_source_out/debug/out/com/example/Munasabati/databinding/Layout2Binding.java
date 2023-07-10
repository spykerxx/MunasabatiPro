// Generated by view binder compiler. Do not edit!
package com.example.Munasabati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.Munasabati.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class Layout2Binding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView ivEventimage;

  @NonNull
  public final TextView textViewServicePrice;

  @NonNull
  public final TextView tvServiceName;

  @NonNull
  public final TextView tvServiceProvider;

  private Layout2Binding(@NonNull LinearLayout rootView, @NonNull ImageView ivEventimage,
      @NonNull TextView textViewServicePrice, @NonNull TextView tvServiceName,
      @NonNull TextView tvServiceProvider) {
    this.rootView = rootView;
    this.ivEventimage = ivEventimage;
    this.textViewServicePrice = textViewServicePrice;
    this.tvServiceName = tvServiceName;
    this.tvServiceProvider = tvServiceProvider;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static Layout2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static Layout2Binding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static Layout2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iv_eventimage;
      ImageView ivEventimage = ViewBindings.findChildViewById(rootView, id);
      if (ivEventimage == null) {
        break missingId;
      }

      id = R.id.textViewServicePrice;
      TextView textViewServicePrice = ViewBindings.findChildViewById(rootView, id);
      if (textViewServicePrice == null) {
        break missingId;
      }

      id = R.id.tv_ServiceName;
      TextView tvServiceName = ViewBindings.findChildViewById(rootView, id);
      if (tvServiceName == null) {
        break missingId;
      }

      id = R.id.tv_serviceProvider;
      TextView tvServiceProvider = ViewBindings.findChildViewById(rootView, id);
      if (tvServiceProvider == null) {
        break missingId;
      }

      return new Layout2Binding((LinearLayout) rootView, ivEventimage, textViewServicePrice,
          tvServiceName, tvServiceProvider);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}