package com.example.adknowvasample_kotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.adknowva.adlib.ANClickThroughAction
import com.adknowva.adlib.AdListener
import com.adknowva.adlib.BackAdListener
import com.adknowva.adlib.BannerAdView
import com.adknowva.adlib.InterstitialAdView
import com.adknowva.adlib.NativeAdResponse
import com.adknowva.adlib.ResultCode

class MainActivity : AppCompatActivity() {

    // TODO - Adknowva SDK Library
    lateinit var bav: BannerAdView
    // TODO - Adknowva SDK Library

    private var layout : RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO - Adknowva SDK Library
        setAdknowvaAD() // AdKnowva SDK init - Apply to Activivty onCreate position.
        // TODO - Adknowva SDK Library

        findViewById<View>(R.id.load_iad_btn).setOnClickListener {
            // 전면광고 샘플
            launchInterstitialAd()
        }

    }


    // TODO - Adknowva SDK Library
    private fun setAdknowvaAD() {
        /*
            With checking the implementation guide below, please apply Implementation either only Dynamic or Static.
            For the “test” value below, please go to http://ssp.huvle.com/ to sign up > create media > Test your app after typing zoneid. Next, contact Huvle before releasing your app for authentication. You must not change the banner size.
        */

//        // When if apply Dynamic Implementation BannerAdView Start
//        bav = BannerAdView(this)
//        layout = findViewById<View>(R.id.adview_container) as RelativeLayout
//        val layoutParams = RelativeLayout.LayoutParams(
//            RelativeLayout.LayoutParams.WRAP_CONTENT,
//            RelativeLayout.LayoutParams.WRAP_CONTENT
//        )
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
//        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
//        bav!!.layoutParams = layoutParams
//        layout!!.addView(bav)

        // When if apply Dynamic Implementation BannerAdView Start
        bav = findViewById(R.id.banner_view)
        bav.placementID = "test" // 320*50 banner testID , 300*250 banner test ID "testbig"
        bav.shouldServePSAs = false
        bav.clickThroughAction = ANClickThroughAction.OPEN_DEVICE_BROWSER
        bav.setAdSize(320, 50) //bav.setAdSize(300, 250);
        // Resizes the container size to fit the banner ad
        bav.resizeAdToFitContainer = true
//        bav.setExpandsToFitScreenWidth(true)
        val adListener: AdListener = object : AdListener {
            override fun onAdRequestFailed(
                bav: com.adknowva.adlib.AdView,
                errorCode: ResultCode
            ) {
                if (errorCode == null) {
                    Log.v("HuvleBANNER", "Call to loadAd failed")
                } else {
                    Log.v("HuvleBANNER", "Ad request failed: $errorCode")
                }
            }
            override fun onAdLoaded(ba: com.adknowva.adlib.AdView) {
                Log.v("HuvleBANNER", "The Ad Loaded!")
            }
            override fun onAdLoaded(nativeAdResponse: NativeAdResponse) {}
            override fun onAdExpanded(bav: com.adknowva.adlib.AdView) {}
            override fun onAdCollapsed(bav: com.adknowva.adlib.AdView) {}
            override fun onAdClicked(bav: com.adknowva.adlib.AdView) {}
            override fun onAdClicked(adView: com.adknowva.adlib.AdView, clickUrl: String) {}
            override fun onLazyAdLoaded(adView: com.adknowva.adlib.AdView) {}
        }
        bav.adListener = adListener
        bav.loadAd()
    }

    //InterstitialAd
    private fun launchInterstitialAd() {
        val iadv = InterstitialAdView(this)
        iadv.backgroundColor = -0x1 // background color
        iadv.closeButtonDelay = 3 * 1000 // Activate close button after 3 seconds
//        iadv.closeButtonDelay = 0         // Activate close button immediately
//        iadv.closeButtonDelay = -1        // Disable close Button

/*
        As for the “testfull” value below, please go to http://ssp.huvle.com/ to sign up > create media > select the 'fullscreen' checkbox > test your app after entering the zoneid corresponding to the 'fullscreen' option.
        Next, contact Huvle before releasing your app for authentication.
        You must not change the banner size.
*/
        iadv.placementID = "testfull" // zoneId
        iadv.shouldServePSAs = false
        iadv.clickThroughAction = ANClickThroughAction.OPEN_DEVICE_BROWSER
        val adListener: AdListener = object : AdListener {
            override fun onAdRequestFailed(
                bav: com.adknowva.adlib.AdView,
                errorCode: ResultCode
            ) {
                if (errorCode == null) {
                    Log.v("HuvleInterstitialAd", "Call to loadAd failed")
                } else {
                    Log.v("HuvleInterstitialAd", "Ad request failed: $errorCode")
                }
            }

            override fun onAdLoaded(ba: com.adknowva.adlib.AdView) {
                Log.v("HuvleInterstitialAd", "The Ad Loaded!")
                iadv.show()
            }

            override fun onAdLoaded(nativeAdResponse: NativeAdResponse) {
                Log.v("HuvleInterstitialAd", "Ad onAdLoaded NativeAdResponse")
            }

            override fun onAdExpanded(bav: com.adknowva.adlib.AdView) {
                Log.v("HuvleInterstitialAd", "Ad expanded")
            }

            override fun onAdCollapsed(bav: com.adknowva.adlib.AdView) {
                Log.v("HuvleInterstitialAd", "Ad collapsed")
            }

            override fun onAdClicked(bav: com.adknowva.adlib.AdView) {
                Log.v("HuvleInterstitialAd", "Ad clicked; opening browser")
            }

            override fun onAdClicked(adView: com.adknowva.adlib.AdView, clickUrl: String) {
                Log.v("HuvleInterstitialAd", "onAdClicked with click URL")
            }

            override fun onLazyAdLoaded(adView: com.adknowva.adlib.AdView) {
                Log.v("HuvleInterstitialAd", "onLazyAdLoaded")
            }
        }
        iadv.adListener = adListener
        iadv.loadAd()
    }

    // backPressed InterstitialAd load
    override fun onBackPressed() {
        launchBackButtonAd()
    }

    private fun launchBackButtonAd() {
        val badv = InterstitialAdView(this)
        bav.setBackgroundColor(-0x1) // background color
//        badv.closeButtonDelay = 10 * 1000 // Activate close button after 10 seconds
        badv.closeButtonDelay = 0         // Activate close button immediately
//        badv.closeButtonDelay = -1        // Disable close Button
        
        badv.placementID = "testfull" // zoneId
        badv.shouldServePSAs = false
        badv.clickThroughAction = ANClickThroughAction.OPEN_DEVICE_BROWSER

        val adListener: AdListener = object : BackAdListener {
            override fun onBackPressed() {
                //return to the app
                Log.v("backIAD", "BackAdListener.onBackPressed()!")
            }

            override fun onAdLoaded(adView: com.adknowva.adlib.AdView) {
                Log.v("backIAD", "The Ad Loaded!")
                badv.show()
            }

            override fun onAdLoaded(nativeAdResponse: NativeAdResponse) {
                Log.v("backIAD", "Ad onAdLoaded NativeAdResponse")
            }

            override fun onAdRequestFailed(
                adView: com.adknowva.adlib.AdView,
                errorCode: ResultCode
            ) {
                if (errorCode == null) {
                    Log.v("backIAD", "Call to loadAd failed")
                } else {
                    Log.v("backIAD", "Ad request failed: $errorCode")
                }
                // end the app if no Ad
                Handler(Looper.getMainLooper()).postDelayed({ finish() }, 0)
            }

            override fun onAdExpanded(adView: com.adknowva.adlib.AdView) {
                Log.v("backIAD", "Ad expanded")
            }

            override fun onAdCollapsed(adView: com.adknowva.adlib.AdView) {
                // Shut down app when you click the Close button
                Handler(Looper.getMainLooper()).postDelayed({ finish() }, 0)
            }

            override fun onAdClicked(adView: com.adknowva.adlib.AdView) {
                Log.v("backIAD", "Ad clicked; opening browser")
            }

            override fun onAdClicked(adView: com.adknowva.adlib.AdView, s: String) {
                Log.v("backIAD", "onAdClicked with click URL")
            }

            override fun onLazyAdLoaded(adView: com.adknowva.adlib.AdView) {
                Log.v("backIAD", "onLazyAdLoaded")
            }
        }

        badv.adListener = adListener
        badv.loadAd()
    }

    // TODO - Adknowva SDK Library


    override fun onDestroy() {
        // TODO - Adknowva SDK Library
        bav.destroy()
        // TODO - Adknowva SDK Library
        super.onDestroy()
    }

}