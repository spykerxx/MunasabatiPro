// Generated by view binder compiler. Do not edit!
package com.example.Munasabati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.Munasabati.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityServiceProviderBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText edSearch2;

  @NonNull
  public final FloatingActionButton fabAddService;

  @NonNull
  public final RecyclerView recyclerProviderServices;

  @NonNull
  public final ImageView sideButton5;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView12;

  @NonNull
  public final TextView textViewHiProvider;

  @NonNull
  public final View view10;

  private ActivityServiceProviderBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText edSearch2, @NonNull FloatingActionButton fabAddService,
      @NonNull RecyclerView recyclerProviderServices, @NonNull ImageView sideButton5,
      @NonNull TextView textView11, @NonNull TextView textView12,
      @NonNull TextView textViewHiProvider, @NonNull View view10) {
    this.rootView = rootView;
    this.edSearch2 = edSearch2;
    this.fabAddService = fabAddService;
    this.recyclerProviderServices = recyclerProviderServices;
    this.sideButton5 = sideButton5;
    this.textView11 = textView11;
    this.textView12 = textView12;
    this.textViewHiProvider = textViewHiProvider;
    this.view10 = view10;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityServiceProviderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityServiceProviderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_service_provider, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityServiceProviderBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edSearch2;
      EditText edSearch2 = ViewBindings.findChildViewById(rootView, id);
      if (edSearch2 == null) {
        break missingId;
      }

      id = R.id.fabAddService;
      FloatingActionButton fabAddService = ViewBindings.findChildViewById(rootView, id);
      if (fabAddService == null) {
        break missingId;
      }

      id = R.id.recyclerProviderServices;
      RecyclerView recyclerProviderServices = ViewBindings.findChildViewById(rootView, id);
      if (recyclerProviderServices == null) {
        break missingId;
      }

      id = R.id.sideButton5;
      ImageView sideButton5 = ViewBindings.findChildViewById(rootView, id);
      if (sideButton5 == null) {
        break missingId;
      }

      id = R.id.textView11;
      TextView textView11 = ViewBindings.findChildViewById(rootView, id);
      if (textView11 == null) {
        break missingId;
      }

      id = R.id.textView12;
      TextView textView12 = ViewBindings.findChildViewById(rootView, id);
      if (textView12 == null) {
        break missingId;
      }

      id = R.id.textViewHiProvider;
      TextView textViewHiProvider = ViewBindings.findChildViewById(rootView, id);
      if (textViewHiProvider == null) {
        break missingId;
      }

      id = R.id.view10;
      View view10 = ViewBindings.findChildViewById(rootView, id);
      if (view10 == null) {
        break missingId;
      }

      return new ActivityServiceProviderBinding((ConstraintLayout) rootView, edSearch2,
          fabAddService, recyclerProviderServices, sideButton5, textView11, textView12,
          textViewHiProvider, view10);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
