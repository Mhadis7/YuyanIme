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
import com.yuyan.imemodule.database.entry.UsedSymbol;
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
public final class UsedSymbolDao_Impl implements UsedSymbolDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UsedSymbol> __insertionAdapterOfUsedSymbol;

  private final EntityDeletionOrUpdateAdapter<UsedSymbol> __deletionAdapterOfUsedSymbol;

  private final EntityDeletionOrUpdateAdapter<UsedSymbol> __updateAdapterOfUsedSymbol;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldest;

  public UsedSymbolDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsedSymbol = new EntityInsertionAdapter<UsedSymbol>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `usedSymbol` (`symbol`,`type`,`time`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UsedSymbol entity) {
        statement.bindString(1, entity.getSymbol());
        statement.bindString(2, entity.getType());
        statement.bindLong(3, entity.getTime());
      }
    };
    this.__deletionAdapterOfUsedSymbol = new EntityDeletionOrUpdateAdapter<UsedSymbol>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `usedSymbol` WHERE `symbol` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UsedSymbol entity) {
        statement.bindString(1, entity.getSymbol());
      }
    };
    this.__updateAdapterOfUsedSymbol = new EntityDeletionOrUpdateAdapter<UsedSymbol>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `usedSymbol` SET `symbol` = ?,`type` = ?,`time` = ? WHERE `symbol` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UsedSymbol entity) {
        statement.bindString(1, entity.getSymbol());
        statement.bindString(2, entity.getType());
        statement.bindLong(3, entity.getTime());
        statement.bindString(4, entity.getSymbol());
      }
    };
    this.__preparedStmtOfDeleteOldest = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM usedSymbol WHERE symbol IN ( SELECT symbol FROM usedSymbol WHERE type = ? ORDER BY time ASC LIMIT ?)";
        return _query;
      }
    };
  }

  @Override
  public void insert(final UsedSymbol bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsedSymbol.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<? extends UsedSymbol> bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsedSymbol.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final UsedSymbol bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUsedSymbol.handle(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final UsedSymbol bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUsedSymbol.handle(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteOldest(final String type, final int overflow) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldest.acquire();
    int _argIndex = 1;
    _stmt.bindString(_argIndex, type);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, overflow);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteOldest.release(_stmt);
    }
  }

  @Override
  public List<UsedSymbol> getAllUsedSymbol() {
    final String _sql = "select * from usedSymbol where type = 'symbol' ORDER BY time DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final List<UsedSymbol> _result = new ArrayList<UsedSymbol>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final UsedSymbol _item;
        final String _tmpSymbol;
        _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        final long _tmpTime;
        _tmpTime = _cursor.getLong(_cursorIndexOfTime);
        _item = new UsedSymbol(_tmpSymbol,_tmpType,_tmpTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<UsedSymbol> getAllSymbolEmoji() {
    final String _sql = "select * from usedSymbol where type = 'emoji' ORDER BY time DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final List<UsedSymbol> _result = new ArrayList<UsedSymbol>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final UsedSymbol _item;
        final String _tmpSymbol;
        _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        final long _tmpTime;
        _tmpTime = _cursor.getLong(_cursorIndexOfTime);
        _item = new UsedSymbol(_tmpSymbol,_tmpType,_tmpTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getCount(final String type) {
    final String _sql = "SELECT COUNT(*) FROM usedSymbol where type = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, type);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
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
