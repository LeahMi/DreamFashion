package com.dvora.myapplicationn.interfaces;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public interface CallBackFragment {
    void showActivity( Class activity);
    void showFragment(int fragmentID);
    void showFragment(int fragmentID, Bundle bundle);
}
