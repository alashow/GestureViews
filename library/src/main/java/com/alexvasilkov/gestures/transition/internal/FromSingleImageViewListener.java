/*
 * Copyright (c) 2016  Alashov Berkeli
 * It is licensed under GNU GPL v. 2 or later. For full terms see the file LICENSE.
 */

package com.alexvasilkov.gestures.transition.internal;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.alexvasilkov.gestures.animation.ViewPositionAnimator;
import com.alexvasilkov.gestures.transition.ViewsCoordinator;
import com.alexvasilkov.gestures.transition.ViewsTracker;
import com.alexvasilkov.gestures.transition.ViewsTransitionAnimator;

public class FromSingleImageViewListener<ID> implements ViewsCoordinator.OnRequestViewListener<ID> {

    private final ImageView mImageView;
    private final ViewsTracker<ID> mTracker;
    private final ViewsTransitionAnimator<ID> mAnimator;

    public FromSingleImageViewListener(@NonNull ImageView listView,
                                       @NonNull ViewsTracker<ID> tracker,
                                       @NonNull ViewsTransitionAnimator<ID> animator) {
        mImageView = listView;
        mTracker = tracker;
        mAnimator = animator;

        mAnimator.addPositionUpdateListener(new UpdateListener());
    }

    @Override
    public void onRequestView(@NonNull ID id) {
        mAnimator.setFromView(id, mImageView);
    }

    private class UpdateListener implements ViewPositionAnimator.PositionUpdateListener {
        @Override
        public void onPositionUpdate(float state, boolean isLeaving) {
            mImageView.setVisibility(state == 1f && ! isLeaving ? View.INVISIBLE : View.VISIBLE);
        }
    }
}
