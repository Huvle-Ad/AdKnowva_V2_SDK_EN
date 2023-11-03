package com.huvle.adknowva_sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.adknowva.adlib.ANClickThroughAction;
import com.adknowva.adlib.AdListener;
import com.adknowva.adlib.AdView;
import com.adknowva.adlib.BannerAdView;
import com.adknowva.adlib.NativeAdResponse;
import com.adknowva.adlib.ResultCode;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MediationSampleActivity extends Activity {
    // TODO - Adknowva SDK Library
    private BannerAdView bav;
    // TODO - Adknowva SDK Library

    private RelativeLayout layout;
    private com.google.android.gms.ads.AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediation);

        // TODO - Adknowva SDK Library
        setAdknowvaAD(); // AdKnowva SDK init - Apply to Activivty onCreate position.
        // TODO - Adknowva SDK Library

    }

    private void setAdknowvaAD(){

 /*
    With checking the implementation guide below, please apply Implementation either only Dynamic or Static.
    For the “test” value below, please go to http://ssp.huvle.com/ to sign up > create media > Test your app after typing zoneid.
    Next, contact Huvle before releasing your app for authentication.
    You must not change the banner size.
*/

//        When if apply Dynamic Implementation BannerAdView Start
//        bav = new BannerAdView(this);
//        layout = (RelativeLayout) findViewById(R.id.adview_container);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        bav.setLayoutParams(layoutParams);
//        layout.addView(bav);

        // When if apply Static Implementation BannerAdView Start
        bav = findViewById(R.id.banner_view2);
        bav.setPlacementID("test"); // 320*50 banner testID , 300*250 banner test ID "testbig"
        bav.setShouldServePSAs(false);
        bav.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER);
        bav.setAdSize(320, 50); //bav.setAdSize(300, 250);
        // Resizes the container size to fit the banner ad
        bav.setResizeAdToFitContainer(true);
//        bav.setExpandsToFitScreenWidth(true);
        AdListener adListener = new AdListener() {
            @Override
            public void onAdRequestFailed(AdView bav, ResultCode errorCode) {
                if (errorCode == null) {
                    Log.v("HuvleBANNER", "Call to loadAd failed");
                } else {
                    Log.v("HuvleBANNER", "Ad request failed: " + errorCode);
                }
                bav.setVisibility(View.GONE);
                setGoogleAD();
            }

            @Override
            public void onAdLoaded(AdView ba) {
                Log.v("HuvleBANNER", "The Ad Loaded!");
                bav.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAdLoaded(NativeAdResponse nativeAdResponse) {}
            @Override
            public void onAdExpanded(AdView bav) {}
            @Override
            public void onAdCollapsed(AdView bav) {}
            @Override
            public void onAdClicked(AdView bav) {}
            @Override
            public void onAdClicked(AdView adView, String clickUrl) {}
            @Override
            public void onLazyAdLoaded(AdView adView) {}
        };
        bav.setAdListener(adListener);
        bav.loadAd();

    }


    private void setGoogleAD(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        mAdView = findViewById(R.id.gadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override public void onAdLoaded() {
                Log.v("GoogleAD", "The Ad Loaded!");
                mAdView.setVisibility(View.VISIBLE);

            }
            @Override public void onAdFailedToLoad(LoadAdError adError) {
                Log.v("GoogleAD", "The Ad failed!");
            }
            @Override public void onAdOpened() {}
            @Override public void onAdClicked() {}
            @Override public void onAdClosed() {}
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bav != null) {
            bav.activityOnResume();
        }
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    protected void onPause() {
        if (bav != null) {
            bav.activityOnPause();
        }
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (bav != null) {
            bav.destroy();
        }
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
