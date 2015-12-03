package com.tacademy.qodbtn41.gosurf.manager.data;

import android.provider.BaseColumns;

/**
 * Created by dongja94 on 2015-10-21.
 */
public class SpotDB {
    public interface AddessTable extends BaseColumns {
        public static final String TABLE_NAME = "spotTable";
        public static final String COLUMN_NAME = "spot";
        public static final String COLUMN_WIND_DIR = "wind_dir";
        public static final String COLUMN_WAVE_DIR = "wave_dir";
    }
}
