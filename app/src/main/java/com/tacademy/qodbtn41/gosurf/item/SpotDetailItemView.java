package com.tacademy.qodbtn41.gosurf.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.qodbtn41.gosurf.R;
import com.tacademy.qodbtn41.gosurf.data.SpotDetailItem;

/**
 * Created by UserPC on 2015-11-01.
 */
public class SpotDetailItemView extends FrameLayout{
    TextView textTime;
    TextView textStatus;
    TextView textWave;
    ImageView imageWindDir;
    ImageView imageWaveDir;
    ImageView imageWindDirGreat;
    ImageView imageWaveDirGreat;
    TextView textWindDir;
    TextView textWaveDir;

    public static final int WEATHER_GREAT = 1;
    public static final int WEATHER_NORMAL = 2;
    public static final int WEATHER_BAD = 3;
    public static final int WEATHER_WARNING = 4;

    int viewVisiblity = GONE;

    public SpotDetailItemView(Context context) {
        super(context);
        init();
    }

    public SpotDetailItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.item_spot_detail, this);
        textTime = (TextView)findViewById(R.id.text_time);
        textStatus = (TextView)findViewById(R.id.text_status);
        textWave = (TextView)findViewById(R.id.text_wave);
        imageWindDir = (ImageView)findViewById(R.id.image_wind_dir);
        imageWaveDir = (ImageView)findViewById(R.id.image_wave_dir);
        imageWindDirGreat = (ImageView)findViewById(R.id.image_wind_dir_great);
        imageWaveDirGreat = (ImageView)findViewById(R.id.image_wave_dir_great);
        textWindDir = (TextView)findViewById(R.id.text_wind_dir);
        textWaveDir = (TextView)findViewById(R.id.text_wave_period);
        setClickable(false);
    }
    public void setData(SpotDetailItem data){
        textTime.setText(data.getTime());
        setStatus(data.getWeather_indicator());
        textWave.setText(data.getSwell_height() + " 미터");
        textWindDir.setText("시속 " + data.getWind_dir() + "m/h");
        textWaveDir.setText("간격 " + data.getSwell_period() + "초");

        setWindDir(data.getWind_dir());
        setWaveDir(data.getSwell_dir());
        setImageWindDirGreat(data.getWind_dir_optimum());
        setImageWaveDirGreat(data.getSwell_dir_optimum());


    }
    private void setImageWindDirGreat(int wind_dir){
        switch (wind_dir){
            case 0:{
                imageWindDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.n));
                break;
            }
            case 1:{
                imageWindDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.ne));
                break;
            }
            case 2:{
                imageWindDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.e));
                break;
            }
            case 3:{
                imageWindDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.se));
                break;
            }
            case 4:{
                imageWindDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.s));
                break;
            }
            case 5:{
                imageWindDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.sw));
                break;
            }
            case 6:{
                imageWindDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.w));
                break;
            }
            case 7:{
                imageWindDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.nw));
                break;
            }
        }
    }
    private void setImageWaveDirGreat(int wave_dir){
        switch (wave_dir) {
            case 0: {
                imageWaveDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.n));
                break;
            }
            case 1: {
                imageWaveDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.ne));
                break;
            }
            case 2: {
                imageWaveDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.e));
                break;
            }
            case 3: {
                imageWaveDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.se));
                break;
            }
            case 4: {
                imageWaveDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.s));
                break;
            }
            case 5: {
                imageWaveDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.sw));
                break;
            }
            case 6: {
                imageWaveDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.w));
                break;
            }
            case 7: {
                imageWaveDirGreat.setImageDrawable(getResources().getDrawable(R.drawable.nw));
                break;
            }
        }
    }

    private void setWaveDir(int swell_dir) {
        switch (swell_dir) {
            case 0: {
                imageWaveDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_n));
                break;
            }
            case 1: {
                imageWaveDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_ne));
                break;
            }
            case 2: {
                imageWaveDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_e));
                break;
            }
            case 3: {
                imageWaveDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_se));
                break;
            }
            case 4: {
                imageWaveDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_s));
                break;
            }
            case 5: {
                imageWaveDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_sw));
                break;
            }
            case 6: {
                imageWaveDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_w));
                break;
            }
            case 7: {
                imageWaveDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_nw));
                break;
            }
        }
    }

    private void setWindDir(int wind_dir) {
        switch (wind_dir){
            case 0:{
                imageWindDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_n));
                break;
            }
            case 1:{
                imageWindDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_ne));
                break;
            }
            case 2:{
                imageWindDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_e));
                break;
            }
            case 3:{
                imageWindDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_se));
                break;
            }
            case 4:{
                imageWindDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_s));
                break;
            }
            case 5:{
                imageWindDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_sw));
                break;
            }
            case 6:{
                imageWindDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_w));
                break;
            }
            case 7:{
                imageWindDir.setImageDrawable(getResources().getDrawable(R.drawable.arrow_nw));
                break;
            }
        }
    }

    private void setStatus(int weatherIndicator){
        switch(weatherIndicator){
            case WEATHER_GREAT :{
                textStatus.setText(getResources().getString(R.string.status_great));
                textStatus.setTextColor(getResources().getColor(R.color.status_text_color_great));
                break;
            }
            case 0:
            case WEATHER_NORMAL : {
                textStatus.setText(getResources().getString(R.string.status_normal));
                textStatus.setTextColor(getResources().getColor(R.color.status_text_color_normal));
                break;
            }
            case WEATHER_BAD : {
                textStatus.setText(getResources().getString(R.string.status_bad));
                textStatus.setTextColor(getResources().getColor(R.color.status_text_color_bad));
                break;
            }
            case WEATHER_WARNING : {
                textStatus.setText(getResources().getString(R.string.status_warning));
                textStatus.setTextColor(getResources().getColor(R.color.status_text_color_warning));
                break;
            }
        }
    }
}
