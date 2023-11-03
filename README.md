# AdKnowva_V2_SDK_android

## AdKnowva Install Guide         


AdKnowva_V2 has an integration method based on Gradle. Plus, we supply sample application, thus you may easily integrate our service through those examples. 
You can check full contents of guide documents by downloading the files from the **“Download All AdKnowva_V2 Sample Projects”** menu below. 
Currently, the latest version is **1.5.2**.



## Affiliate Application
We will help you know how to affiliate with AdKnowva_V2 please visit this URL. https://www.huvleview.com/doc/contact.php


### Integration Guide
- Please refer to Usages or the sample project below.
- [Download All AdKnowva_V2 Sample Projects](https://github.com/Huvle-Ad/AdKnowva_V2_SDK_EN/archive/main.zip)    
-> AdKnowva_V2 / AdKnowva_V2 + HuvleSDK  
- [Unity Plugin](https://github.com/Huvle-Ad/AdKnowva_V2_SDK_EN/releases/tag/1.5.2)


## Usages
### 1. Manifest
- Add networkSecurityConfig 
```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="com.google.android.gms.permission.AD_ID" /> 

<application
	.
	.
	android:networkSecurityConfig="@xml/network"
	tools:replace="android:networkSecurityConfig"
	.
	.
	
```

### 2. Add SDK 
Please add sub library including SDK into Gradle to use AdKnowva SDK.
- build.gradle(Project)
```
allprojects {
    repositories {
        google()
        maven {
            name "Huvle"
            url "https://sdk.huvle.com/repository/internal"
        }
    }
}
```

- build.gradle(app)
```
android {
    ...
    defaultConfig {
        .
	.
        multiDexEnabled true
	.
	.
    }
}

dependencies {
	.
	.
	/**
	* adknowva sdk , play-service-ads 
	*/
	implementation 'com.google.android.gms:play-services-ads:20.6.0' // Please use that version or higher.
	implementation 'com.adknowva.adlib:AdknowvaAdLib:1.5.2' // Please implement after checking the latest version.
	.
	.
}
```

### 3. Apply to your app

>#### Banner Ad
- Activity in which the advertisement will be applied.
- Java code
```java
// TODO - Adknowva SDK Library
private BannerAdView bav;
// TODO - Adknowva SDK Library

@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate( savedInstanceState );
  setContentView( R.layout.activity_main );

  // TODO - Adknowva SDK Library  
  setAdknowvaAD(); // AdKnowva SDK init - Apply Activivty onCreate position.
  // TODO - Adknowva SDK Library
}

private void setAdknowvaAD() {

// For the "test" value below, 
// please go to https://ssp.huvle.com/ to sign up > create media > Test your app after typing zoneid.
// Next, contact Huvle before releasing your app for authentication. You must not change the banner size.

  bav = findViewById(R.id.banner_view);
  bav.setPlacementID("test"); // 320*50 banner testID , 300*250 banner test ID "testbig"
  bav.setShouldServePSAs(false);
  bav.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER);
  bav.setAdSize(320, 50); //bav.setAdSize(300, 250);
  // Resizes the container size to fit the banner ad
  bav.setResizeAdToFitContainer(true);
// bav.setExpandsToFitScreenWidth(true);
  AdListener adListener = new AdListener() {
      @Override
      public void onAdRequestFailed(AdView bav, ResultCode errorCode) {
          if (errorCode == null) {
              Log.v("HuvleBANNER", "Call to loadAd failed");
          } else {
              Log.v("HuvleBANNER", "Ad request failed: " + errorCode);
          }
      }

      @Override
      public void onAdLoaded(AdView ba) {Log.v("HuvleBANNER", "The Ad Loaded!");}
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


@Override
protected void onPause() {
    // TODO - Adknowva SDK Library
    if (bav != null) {
        bav.activityOnPause();
    }
    // TODO - Adknowva SDK Library
    super.onPause();
}

@Override
protected void onResume() {
    // TODO - Adknowva SDK Library
    if (bav != null) {
        bav.activityonResume();
    }
    // TODO - Adknowva SDK Library
    super.onResume();
}

@Override
protected void onDestroy() {
    
    // TODO - Adknowva SDK Library
    if (bav != null) {
        bav.destroy();
    }
    // TODO - Adknowva SDK Library
    super.onDestroy();
}

```

- Kotlin code
```java
// TODO - Adknowva SDK Library
lateinit var bav: BannerAdView
// TODO - Adknowva SDK Library

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // TODO - Adknowva SDK Library
    setAdknowvaAD() // AdKnowva SDK init - Apply to Activivty onCreate position.
    // TODO - Adknowva SDK Library
}

// TODO - Adknowva SDK Library
private fun setAdknowvaAD() {
/*
  With checking the implementation guide below, please apply Implementation either only Dynamic or Static.
  For the “test” value below, please go to http://ssp.huvle.com/ to sign up > create media > Test your app after typing zoneid. Next, contact Huvle before releasing your app for authentication. You must not change the banner size.
*/
  bav = findViewById(R.id.banner_view)
  bav.setPlacementID("test") // 320*50 banner testID , 300*250 banner test ID "testbig"
  bav.setShouldServePSAs(false)
  bav.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER)
  bav.setAdSize(320, 50) //bav.setAdSize(300, 250);
  // Resizes the container size to fit the banner ad
  bav.setResizeAdToFitContainer(true)
// bav.setExpandsToFitScreenWidth(true)
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
  bav.setAdListener(adListener)
  bav.loadAd()
}


override fun onPause() {
    bav.activityOnPause()
    super.onPause()
}

override fun onResume() {
    bav.activityOnResume()
    super.onResume()
}

override fun onDestroy() {
    bav.destroy()
    super.onDestroy()
    
}

```

>#### InterstitialAd
+ Please refer to the example sample for full application.
- Activity in which the advertisement will be applied.

```java

private void launchInterstitialAd() {
        final InterstitialAdView iadv = new InterstitialAdView(this);
        iadv.setBackgroundColor(0xffffffff); // background color
        iadv.setCloseButtonDelay(10 * 1000);  // Activate close button after 10 seconds
        //iadv.setCloseButtonDelay(0);        // Activate close button immediately
        //iadv.setCloseButtonDelay(-1);       // Disable close Button
/*
        As for the “testfull” value below, please go to http://ssp.huvle.com/ to sign up > create media > select the 'fullscreen' checkbox > test your app after entering the zoneid corresponding to the 'fullscreen' option.
        Next, contact Huvle before releasing your app for authentication.
        You must not change the banner size.
 */
        iadv.setPlacementID("testfull"); // zoneId
        iadv.setShouldServePSAs(false);
        iadv.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER);


        AdListener adListener = new AdListener() {
            @Override
            public void onAdRequestFailed(AdView bav, ResultCode errorCode) {
                if (errorCode == null) {
                    Log.v("HuvleInterstitialAd", "Call to loadAd failed");
                } else {
                    Log.v("HuvleInterstitialAd", "Ad request failed: " + errorCode);
                }
            }

            @Override
            public void onAdLoaded(AdView ba) {
                Log.v("HuvleInterstitialAd", "The Ad Loaded!");
                iadv.show();
            }

            @Override
            public void onAdLoaded(NativeAdResponse nativeAdResponse) {
                Log.v("HuvleInterstitialAd", "Ad onAdLoaded NativeAdResponse");
            }

            @Override
            public void onAdExpanded(AdView bav) {
                Log.v("HuvleInterstitialAd", "Ad expanded");
            }

            @Override
            public void onAdCollapsed(AdView bav) {
                Log.v("HuvleInterstitialAd", "Ad collapsed");
            }

            @Override
            public void onAdClicked(AdView bav) {
                Log.v("HuvleInterstitialAd", "Ad clicked; opening browser");
            }

            @Override
            public void onAdClicked(AdView adView, String clickUrl) {
                Log.v("HuvleInterstitialAd", "onAdClicked with click URL");
            }

            @Override
            public void onLazyAdLoaded(AdView adView) {
                Log.v("HuvleInterstitialAd", "onLazyAdLoaded");
            }
        };
        iadv.setAdListener(adListener);
        iadv.loadAd();
    }

```

>### InterstitialAd_BackButton Setting
+ Please refer to the example sample for full application.
- Activity in which the advertisement will be applied.
* use BackAdListener

```java

// backPressed InterstitialAd load
    @Override
    public void onBackPressed() {
        launchBackButtonAd();
    }

    private void launchBackButtonAd() {
        final InterstitialAdView badv = new InterstitialAdView(this);
//        bav.setBackgroundColor(0xffffffff);

        badv.setCloseButtonDelay(10 * 1000);  // Activate close button after 10 seconds
        //badv.setCloseButtonDelay(0);        // Activate close button immediately
        //badv.setCloseButtonDelay(-1);       // Disable close Button


        badv.setPlacementID("testfull"); //ZoneId
        badv.setShouldServePSAs(false);
        badv.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER);

        AdListener adListener = new BackAdListener() {
            @Override
            public void onBackPressed() {
                //return to the app
                Log.v("backIAD", "BackAdListener.onBackPressed()!");
            }

            @Override
            public void onAdLoaded(AdView adView) {
                Log.v("backIAD", "The Ad Loaded!");
                badv.show();

            }

            @Override
            public void onAdLoaded(NativeAdResponse nativeAdResponse) {
                Log.v("backIAD", "Ad onAdLoaded NativeAdResponse");
            }

            @Override
            public void onAdRequestFailed(AdView adView, ResultCode errorCode) {
                if (errorCode == null) {
                    Log.v("backIAD", "Call to loadAd failed");
                } else {
                    Log.v("backIAD", "Ad request failed: " + errorCode);
                }
                // end the app if no Ad
                finish();
            }

            @Override
            public void onAdExpanded(AdView adView) {
                Log.v("backIAD", "Ad expanded");
            }

            @Override
            public void onAdCollapsed(AdView adView) {
                // Shut down app when you click the Close button
                finish();
            }

            @Override
            public void onAdClicked(AdView adView) {
                Log.v("backIAD", "Ad clicked; opening browser");
            }

            @Override
            public void onAdClicked(AdView adView, String s) {
                Log.v("backIAD", "onAdClicked with click URL");
            }

            @Override
            public void onLazyAdLoaded(AdView adView) {
                Clog.v("backIAD", "onLazyAdLoaded");
            }
        };

        badv.setAdListener(adListener);
        badv.loadAd();
    }

```





## License
Huvle Corporation owns the copyright on AdKnowva_V2 SDK.
```
AdKnowva_V2 SDK Android
Copyright 2021-present Huvle Corp.

Unauthorized use, modification and redistribution of this software are strongly prohibited.
```

