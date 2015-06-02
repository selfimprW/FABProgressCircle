/*
 * Copyright (C) 2015 Jorge Castillo Pérez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jorgecastilloprz.library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ProgressBar;

/**
 * This view contains the animated arc and depends totally on {@link ProgressArcDrawable} to get
 * its corresponding graphic aspect.
 *
 * @author Jorge Castillo Pérez
 */
final class ProgressArcView extends ProgressBar {

  private int arcColor;
  private int arcWidth;

  private InternalListener internalListener;

  ProgressArcView(Context context, int arcColor, int arcWidth) {
    super(context);
    this.arcColor = arcColor;
    this.arcWidth = arcWidth;
    init();
  }

  private void init() {
    setupInitialAlpha();
    ProgressArcDrawable arcDrawable = new ProgressArcDrawable(getResources(), arcWidth, arcColor);
    setIndeterminateDrawable(arcDrawable);
  }

  private void setupInitialAlpha() {
    setAlpha(0);
  }

  void setInternalListener(InternalListener internalListener) {
    this.internalListener = internalListener;
  }

  void show() {
    postDelayed(new Runnable() {
      @Override public void run() {
        setAlpha(1);
        getDrawable().reset();
      }
    }, Utils.SHOW_SCALE_ANIM_DELAY);
  }

  void requestCompleteAnimation() {
    getDrawable().requestCompleteAnimation(internalListener);
  }

  private ProgressArcDrawable getDrawable() {
    Drawable ret = getIndeterminateDrawable();
    return (ProgressArcDrawable) ret;
  }
}
