package com.yuyan.imemodule.database.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.yuyan.imemodule.database.entry.SideSymbol;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SideSymbolDao_Impl implements SideSymbolDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SideSymbol> __insertionAdapterOfSideSymbol;

  private final EntityDeletionOrUpdateAdapter<SideSymbol> __deletionAdapterOfSideSymbol;

  private final EntityDeletionOrUpdateAdapter<SideSymbol> __updateAdapterOfSideSymbol;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByKey;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSymbol;

  public SideSymbolDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSideSymbol = new EntityInsertionAdapter<SideSymbol>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `side_symbol` (`symbolKey`,`symbolValue`,`type`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SideSymbol entity) {
        statement.bindString(1, entity.getSymbolKey());
        statement.bindString(2, entity.getSymbolValue());
        statement.bindString(3, entity.getType());
      }
    };
    this.__deletionAdapterOfSideSymbol = new EntityDeletionOrUpdateAdapter<SideSymbol>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `side_symbol` WHERE `symbolKey` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SideSymbol entity) {
        statement.bindString(1, entity.getSymbolKey());
      }
    };
    this.__updateAdapterOfSideSymbol = new EntityDeletionOrUpdateAdapter<SideSymbol>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `side_symbol` SET `symbolKey` = ?,`symbolValue` = ?,`type` = ? WHERE `symbolKey` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SideSymbol entity) {
        statement.bindString(1, entity.getSymbolKey());
        statement.bindString(2, entity.getSymbolValue());
        statement.bindString(3, entity.getType());
        statement.bindString(4, entity.getSymbolKey());
      }
    };
    this.__preparedStmtOfDeleteByKey = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from side_symbol where symbolKey = ? AND type = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from side_symbol where type = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSymbol = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "update side_symbol set symbolValue =? where symbolKey =? AND type = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final SideSymbol bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSideSymbol.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<? extends SideSymbol> bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSideSymbol.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final SideSymbol bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSideSymbol.handle(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final SideSymbol bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfSideSymbol.handle(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteByKey(final String key, final String type) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByKey.acquire();
    int _argIndex = 1;
    _stmt.bindString(_argIndex, key);
    _argIndex = 2;
    _stmt.bindString(_argIndex, type);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteByKey.release(_stmt);
    }
  }

  @Override
  public void deleteAll(final String type) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    int _argIndex = 1;
    _stmt.bindString(_argIndex, type);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public void updateSymbol(final String key, final String value, final String type) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSymbol.acquire();
    int _argIndex = 1;
    _stmt.bindString(_argIndex, value);
    _argIndex = 2;
    _stmt.bindString(_argIndex, key);
    _argIndex = 3;
    _stmt.bindString(_argIndex, type);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateSymbol.release(_stmt);
    }
  }

  @Override
  public SideSymbol getByKey(final String key, final String type) {
    final String _sql = "select * from side_symbol where symbolKey = ? AND type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, key);
    _argIndex = 2;
    _statement.bindString(_argIndex, type);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSymbolKey = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolKey");
      final int _cursorIndexOfSymbolValue = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolValue");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final SideSymbol _result;
      if (_cursor.moveToFirst()) {
        final String _tmpSymbolKey;
        _tmpSymbolKey = _cursor.getString(_cursorIndexOfSymbolKey);
        final String _tmpSymbolValue;
        _tmpSymbolValue = _cursor.getString(_cursorIndexOfSymbolValue);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _result = new SideSymbol(_tmpSymbolKey,_tmpSymbolValue,_tmpType);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<SideSymbol> getAllSideSymbolNumber() {
    final String _sql = "select * from side_symbol where type = 'number'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSymbolKey = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolKey");
      final int _cursorIndexOfSymbolValue = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolValue");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final List<SideSymbol> _result = new ArrayList<SideSymbol>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final SideSymbol _item;
        final String _tmpSymbolKey;
        _tmpSymbolKey = _cursor.getString(_cursorIndexOfSymbolKey);
        final String _tmpSymbolValue;
        _tmpSymbolValue = _cursor.getString(_cursorIndexOfSymbolValue);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _item = new SideSymbol(_tmpSymbolKey,_tmpSymbolValue,_tmpType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<SideSymbol> getAllSideSymbolPinyin() {
    final String _sql = "select * from side_symbol where type = 'pinyin'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSymbolKey = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolKey");
      final int _cursorIndexOfSymbolValue = CursorUtil.getColumnIndexOrThrow(_cursor, "symbolValue");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final List<SideSymbol> _result = new ArrayList<SideSymbol>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final SideSymbol _item;
        final String _tmpSymbolKey;
        _tmpSymbolKey = _cursor.getString(_cursorIndexOfSymbolKey);
        final String _tmpSymbolValue;
        _tmpSymbolValue = _cursor.getString(_cursorIndexOfSymbolValue);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _item = new SideSymbol(_tmpSymbolKey,_tmpSymbolValue,_tmpType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
