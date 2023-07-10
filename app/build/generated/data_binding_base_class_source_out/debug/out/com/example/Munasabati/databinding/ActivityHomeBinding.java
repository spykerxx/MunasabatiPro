// Generated by view binder compiler. Do not edit!
package com.example.Munasabati.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.Munasabati.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView currentResv2;

  @NonNull
  public final EditText edSearch;

  @NonNull
  public final ConstraintLayout homeLayout;

  @NonNull
  public final ListView lview;

  @NonNull
  public final BottomNavigationView navHome;

  @NonNull
  public final RecyclerView recyclerHome;

  @NonNull
  public final ImageView sideButton;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textViewSeeAll;

  @NonNull
  public final View view3;

  private ActivityHomeBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView currentResv2,
      @NonNull EditText edSearch, @NonNull ConstraintLayout homeLayout, @NonNull ListView lview,
      @NonNull BottomNavigationView navHome, @NonNull RecyclerView recyclerHome,
      @NonNull ImageView sideButton, @NonNull TextView textView2, @NonNull TextView textViewSeeAll,
      @NonNull View view3) {
    this.rootView = rootView;
    this.currentResv2 = currentResv2;
    this.edSearch = edSearch;
    this.homeLayout = homeLayout;
    this.lview = lview;
    this.navHome = navHome;
    this.recyclerHome = recyclerHome;
    this.sideButton = sideButton;
    this.textView2 = textView2;
    this.textViewSeeAll = textViewSeeAll;
    this.view3 = view3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.currentResv2;
      ImageView currentResv2 = ViewBindings.findChildViewById(rootView, id);
      if (currentResv2 == null) {
        break missingId;
      }

      id = R.id.edSearch;
      EditText edSearch = ViewBindings.findChildViewById(rootView, id);
      if (edSearch == null) {
        break missingId;
      }

      ConstraintLayout homeLayout = (ConstraintLayout) rootView;

      id = R.id.lview;
      ListView lview = ViewBindings.findChildViewById(rootView, id);
      if (lview == null) {
        break missingId;
      }

      id = R.id.nav_Home;
      BottomNavigationView navHome = ViewBindings.findChildViewById(rootView, id);
      if (navHome == null) {
        break missingId;
      }

      id = R.id.recyclerHome;
      RecyclerView recyclerHome = ViewBindings.findChildViewById(rootView, id);
      if (recyclerHome == null) {
        break missingId;
      }

      id = R.id.sideButton;
      ImageView sideButton = ViewBindings.findChildViewById(rootView, id);
      if (sideButton == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textViewSeeAll;
      TextView textViewSeeAll = ViewBindings.findChildViewById(rootView, id);
      if (textViewSeeAll == null) {
        break missingId;
      }

      id = R.id.view3;
      View view3 = ViewBindings.findChildViewById(rootView, id);
      if (view3 == null) {
        break missingId;
      }

      return new ActivityHomeBinding((ConstraintLayout) rootView, currentResv2, edSearch,
          homeLayout, lview, navHome, recyclerHome, sideButton, textView2, textViewSeeAll, view3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
