package com.yuyan.imemodule.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.yuyan.imemodule.database.dao.ClipboardDao;
import com.yuyan.imemodule.database.dao.ClipboardDao_Impl;
import com.yuyan.imemodule.database.dao.PhraseDao;
import com.yuyan.imemodule.database.dao.PhraseDao_Impl;
import com.yuyan.imemodule.database.dao.SideSymbolDao;
import com.yuyan.imemodule.database.dao.SideSymbolDao_Impl;
import com.yuyan.imemodule.database.dao.SkbFunDao;
import com.yuyan.imemodule.database.dao.SkbFunDao_Impl;
import com.yuyan.imemodule.database.dao.UsedSymbolDao;
import com.yuyan.imemodule.database.dao.UsedSymbolDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DataBaseKT_Impl extends DataBaseKT {
  private volatile SideSymbolDao _sideSymbolDao;

  private volatile ClipboardDao _clipboardDao;

  private volatile UsedSymbolDao _usedSymbolDao;

  private volatile PhraseDao _phraseDao;

  private volatile SkbFunDao _skbFunDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `side_symbol` (`symbolKey` TEXT NOT NULL, `symbolValue` TEXT NOT NULL, `type` TEXT NOT NULL, PRIMARY KEY(`symbolKey`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `clipboard` (`content` TEXT NOT NULL, `isKeep` INTEGER NOT NULL, `time` INTEGER NOT NULL, PRIMARY KEY(`content`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `usedSymbol` (`symbol` TEXT NOT NULL, `type` TEXT NOT NULL, `time` INTEGER NOT NULL, PRIMARY KEY(`symbol`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `phrase` (`content` TEXT NOT NULL, `isKeep` INTEGER NOT NULL, `t9` TEXT NOT NULL, `qwerty` TEXT NOT NULL, `lx17` TEXT NOT NULL, `time` INTEGER NOT NULL, PRIMARY KEY(`content`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `skbfun` (`name` TEXT NOT NULL, `isKeep` INTEGER NOT NULL, `position` INTEGER NOT NULL, PRIMARY KEY(`name`, `isKeep`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c3d3f3080ae87f430f45c03b95b00a67')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `side_symbol`");
        db.execSQL("DROP TABLE IF EXISTS `clipboard`");
        db.execSQL("DROP TABLE IF EXISTS `usedSymbol`");
        db.execSQL("DROP TABLE IF EXISTS `phrase`");
        db.execSQL("DROP TABLE IF EXISTS `skbfun`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsSideSymbol = new HashMap<String, TableInfo.Column>(3);
        _columnsSideSymbol.put("symbolKey", new TableInfo.Column("symbolKey", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSideSymbol.put("symbolValue", new TableInfo.Column("symbolValue", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSideSymbol.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSideSymbol = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSideSymbol = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSideSymbol = new TableInfo("side_symbol", _columnsSideSymbol, _foreignKeysSideSymbol, _indicesSideSymbol);
        final TableInfo _existingSideSymbol = TableInfo.read(db, "side_symbol");
        if (!_infoSideSymbol.equals(_existingSideSymbol)) {
          return new RoomOpenHelper.ValidationResult(false, "side_symbol(com.yuyan.imemodule.database.entry.SideSymbol).\n"
                  + " Expected:\n" + _infoSideSymbol + "\n"
                  + " Found:\n" + _existingSideSymbol);
        }
        final HashMap<String, TableInfo.Column> _columnsClipboard = new HashMap<String, TableInfo.Column>(3);
        _columnsClipboard.put("content", new TableInfo.Column("content", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClipboard.put("isKeep", new TableInfo.Column("isKeep", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClipboard.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysClipboard = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesClipboard = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoClipboard = new TableInfo("clipboard", _columnsClipboard, _foreignKeysClipboard, _indicesClipboard);
        final TableInfo _existingClipboard = TableInfo.read(db, "clipboard");
        if (!_infoClipboard.equals(_existingClipboard)) {
          return new RoomOpenHelper.ValidationResult(false, "clipboard(com.yuyan.imemodule.database.entry.Clipboard).\n"
                  + " Expected:\n" + _infoClipboard + "\n"
                  + " Found:\n" + _existingClipboard);
        }
        final HashMap<String, TableInfo.Column> _columnsUsedSymbol = new HashMap<String, TableInfo.Column>(3);
        _columnsUsedSymbol.put("symbol", new TableInfo.Column("symbol", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsedSymbol.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsedSymbol.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsedSymbol = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsedSymbol = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsedSymbol = new TableInfo("usedSymbol", _columnsUsedSymbol, _foreignKeysUsedSymbol, _indicesUsedSymbol);
        final TableInfo _existingUsedSymbol = TableInfo.read(db, "usedSymbol");
        if (!_infoUsedSymbol.equals(_existingUsedSymbol)) {
          return new RoomOpenHelper.ValidationResult(false, "usedSymbol(com.yuyan.imemodule.database.entry.UsedSymbol).\n"
                  + " Expected:\n" + _infoUsedSymbol + "\n"
                  + " Found:\n" + _existingUsedSymbol);
        }
        final HashMap<String, TableInfo.Column> _columnsPhrase = new HashMap<String, TableInfo.Column>(6);
        _columnsPhrase.put("content", new TableInfo.Column("content", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhrase.put("isKeep", new TableInfo.Column("isKeep", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhrase.put("t9", new TableInfo.Column("t9", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhrase.put("qwerty", new TableInfo.Column("qwerty", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhrase.put("lx17", new TableInfo.Column("lx17", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhrase.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPhrase = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPhrase = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPhrase = new TableInfo("phrase", _columnsPhrase, _foreignKeysPhrase, _indicesPhrase);
        final TableInfo _existingPhrase = TableInfo.read(db, "phrase");
        if (!_infoPhrase.equals(_existingPhrase)) {
          return new RoomOpenHelper.ValidationResult(false, "phrase(com.yuyan.imemodule.database.entry.Phrase).\n"
                  + " Expected:\n" + _infoPhrase + "\n"
                  + " Found:\n" + _existingPhrase);
        }
        final HashMap<String, TableInfo.Column> _columnsSkbfun = new HashMap<String, TableInfo.Column>(3);
        _columnsSkbfun.put("name", new TableInfo.Column("name", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSkbfun.put("isKeep", new TableInfo.Column("isKeep", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSkbfun.put("position", new TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSkbfun = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSkbfun = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSkbfun = new TableInfo("skbfun", _columnsSkbfun, _foreignKeysSkbfun, _indicesSkbfun);
        final TableInfo _existingSkbfun = TableInfo.read(db, "skbfun");
        if (!_infoSkbfun.equals(_existingSkbfun)) {
          return new RoomOpenHelper.ValidationResult(false, "skbfun(com.yuyan.imemodule.database.entry.SkbFun).\n"
                  + " Expected:\n" + _infoSkbfun + "\n"
                  + " Found:\n" + _existingSkbfun);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c3d3f3080ae87f430f45c03b95b00a67", "811834a1d1297723d7f9ecfba60086a2");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "side_symbol","clipboard","usedSymbol","phrase","skbfun");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `side_symbol`");
      _db.execSQL("DELETE FROM `clipboard`");
      _db.execSQL("DELETE FROM `usedSymbol`");
      _db.execSQL("DELETE FROM `phrase`");
      _db.execSQL("DELETE FROM `skbfun`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SideSymbolDao.class, SideSymbolDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ClipboardDao.class, ClipboardDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UsedSymbolDao.class, UsedSymbolDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PhraseDao.class, PhraseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SkbFunDao.class, SkbFunDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public SideSymbolDao sideSymbolDao() {
    if (_sideSymbolDao != null) {
      return _sideSymbolDao;
    } else {
      synchronized(this) {
        if(_sideSymbolDao == null) {
          _sideSymbolDao = new SideSymbolDao_Impl(this);
        }
        return _sideSymbolDao;
      }
    }
  }

  @Override
  public ClipboardDao clipboardDao() {
    if (_clipboardDao != null) {
      return _clipboardDao;
    } else {
      synchronized(this) {
        if(_clipboardDao == null) {
          _clipboardDao = new ClipboardDao_Impl(this);
        }
        return _clipboardDao;
      }
    }
  }

  @Override
  public UsedSymbolDao usedSymbolDao() {
    if (_usedSymbolDao != null) {
      return _usedSymbolDao;
    } else {
      synchronized(this) {
        if(_usedSymbolDao == null) {
          _usedSymbolDao = new UsedSymbolDao_Impl(this);
        }
        return _usedSymbolDao;
      }
    }
  }

  @Override
  public PhraseDao phraseDao() {
    if (_phraseDao != null) {
      return _phraseDao;
    } else {
      synchronized(this) {
        if(_phraseDao == null) {
          _phraseDao = new PhraseDao_Impl(this);
        }
        return _phraseDao;
      }
    }
  }

  @Override
  public SkbFunDao skbFunDao() {
    if (_skbFunDao != null) {
      return _skbFunDao;
    } else {
      synchronized(this) {
        if(_skbFunDao == null) {
          _skbFunDao = new SkbFunDao_Impl(this);
        }
        return _skbFunDao;
      }
    }
  }
}
