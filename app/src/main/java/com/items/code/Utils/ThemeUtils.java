package com.items.code.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import com.items.code.R;
import com.items.code.ui.main.activity.MainActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihongxin on 2017/2/12.
 */

public class ThemeUtils {
    private static final String THEME = "pre_theme";
    public static final String SHARE_PREFERENCES_NAME = ".newme.pre";

    public static void changeTheme(final Activity activity) {

        final ChoiceOnClickListener listener = new ChoiceOnClickListener();
        final int index = 0;
        new AlertDialog.Builder(activity)
                .setTitle("主题选择")
                .setSingleChoiceItems(new ThemeAdapter(activity, R.layout.item_theme, R.id.check, Theme.getThemes()), index, listener)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        setTheme(activity, listener.getWhich());

                        activity.startActivity(new Intent(activity, MainActivity.class));
                        activity.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
    public static void setTheme(Activity activity) {

        setTheme(activity, getTheme(activity));
    }
    public static  void setTheme(Activity activity,int index){
        setTheme(activity,Theme.getThemes().get(index));
        
    }
    public static Theme getTheme(Context context) {
        SharedPreferences preferences = context.getSharedPreferences( SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String tag = preferences.getString(THEME, Theme.getThemes().get(0).tag);
        return getThemeByTag(tag);
    }
    private static Theme getThemeByTag(String tag)  {
        for (Theme t : Theme.getThemes()) {
            if (t.tag.equals(tag)) {
                return t;
            }
        }
        return Theme.getThemes().get(0);
    }
    public static void setTheme(Activity activity,Theme theme){
        if(theme==null){
            return;
        }
        activity.setTheme(theme.style);
        saveTheme(activity, theme);
    }
    private static void saveTheme(Context context, Theme theme) {
        SharedPreferences preferences = context.getSharedPreferences( SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(THEME, theme.tag);
        editor.commit();
    }
    private static class ChoiceOnClickListener implements  DialogInterface.OnClickListener{
        private int which=0;
        @Override
        public void onClick(DialogInterface dialog, int which) {
            this.which=which;
        }
        public int getWhich(){
            return which;
        }
    }

    public static class Theme{
        public String name;
        public String tag;
        public int color;
        public int style;
        private static List<Theme> THEME_MAP=new ArrayList<>();
        static {
            THEME_MAP.add(new Theme("复古绿","Green",R.color.green_accent,R.style.Green_theme));
            THEME_MAP.add(new Theme("夜间黑","Black",R.color.black_primary,R.style.Night_theme));
            THEME_MAP.add(new Theme("中国红","Red",R.color.red_primary,R.style.Red_theme));
            THEME_MAP.add(new Theme("可爱粉","Pink",R.color.pink_primary,R.style.Pink_theme));
            THEME_MAP.add(new Theme("琥珀黄","Yellow",R.color.amber_primary,R.style.Yellow_theme));
            THEME_MAP.add(new Theme("活力蓝","Blue",R.color.blue_primary,R.style.Blue_theme));
        }
            public Theme(String name,String tag,int color,int style){
                this.name=name;
                this.tag=tag;
                this.color=color;
                this.style=style;

            }
        public String toString() {
            return name;
        }
        protected static List<Theme> getThemes(){
            return THEME_MAP;
        }

    }
    private static class ThemeAdapter extends  ArrayAdapter<Theme>{
        private int mTextId;
        public ThemeAdapter(Context context, int resource, int textViewResourceId, List<Theme> objects){
            super(context,resource,textViewResourceId,objects);
            this.mTextId=textViewResourceId;
        }
        public View getView(int position,View convertView,ViewGroup parent){
            Theme theme=getItem(position);
            View view=super.getView(position,convertView,parent);
            CheckedTextView textview= (CheckedTextView) view.findViewById(mTextId);
            textview.setTextColor(getContext().getResources().getColor(theme.color));
            return view;
        }
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

    }

}

