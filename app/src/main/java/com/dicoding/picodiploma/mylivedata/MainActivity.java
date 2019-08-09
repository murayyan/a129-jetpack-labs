/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dicoding.picodiploma.mylivedata;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mLiveDataTimerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLiveDataTimerViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        subscribe();
    }

    private void subscribe() {
        final Observer<Long> elapsedTimeObserver = new Observer<Long>() {
            @Override
            public void onChanged(@Nullable final Long aLong) {
                String newText = MainActivity.this.getResources().getString(R.string.seconds, aLong);
                ((TextView) findViewById(R.id.timer_textview)).setText(newText);
            }
        };

        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver);
    }
}
