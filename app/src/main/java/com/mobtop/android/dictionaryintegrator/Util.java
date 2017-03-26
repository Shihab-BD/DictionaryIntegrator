package com.mobtop.android.dictionaryintegrator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by Shihab on 3/26/2017.
 */

public class Util {
    public static boolean isPackageExisted(Context context, String targetPackage) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }

    public static void launchOrDownload(String packageName, Context context) {

        if (isPackageExisted(context, packageName)) {
            launchApp(packageName, context);
            return;
        }
        browseUrl(context, "https://play.google.com/store/apps/details?id=" + packageName);
    }

    private static void browseUrl(Context context, String url) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW",
                    Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launchApp(String packageName, Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }


    public static void email(Context context, String emailTo, String subject,
                             String emailText) {

        try {

            Intent intent = new Intent();
            intent.setData(Uri.parse("mailto:"));
            intent.setAction(Intent.ACTION_SENDTO);
            intent.putExtra(Intent.EXTRA_EMAIL,
                    new String[]{emailTo});

            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, emailText);

            context.startActivity(Intent.createChooser(intent, "Send mail..."));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
