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
import com.yuyan.imemodule.database.entry.SkbFun;
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
public final class SkbFunDao_Impl implements SkbFunDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SkbFun> __insertionAdapterOfSkbFun;

  private final EntityDeletionOrUpdateAdapter<SkbFun> __deletionAdapterOfSkbFun;

  private final EntityDeletionOrUpdateAdapter<SkbFun> __updateAdapterOfSkbFun;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public SkbFunDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSkbFun = new EntityInsertionAdapter<SkbFun>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `skbfun` (`name`,`isKeep`,`position`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SkbFun entity) {
        statement.bindString(1, entity.getName());
        statement.bindLong(2, entity.isKeep());
        statement.bindLong(3, entity.getPosition());
      }
    };
    this.__deletionAdapterOfSkbFun = new EntityDeletionOrUpdateAdapter<SkbFun>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `skbfun` WHERE `name` = ? AND `isKeep` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SkbFun entity) {
        statement.bindString(1, entity.getName());
        statement.bindLong(2, entity.isKeep());
      }
    };
    this.__updateAdapterOfSkbFun = new EntityDeletionOrUpdateAdapter<SkbFun>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `skbfun` SET `name` = ?,`isKeep` = ?,`position` = ? WHERE `name` = ? AND `isKeep` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SkbFun entity) {
        statement.bindString(1, entity.getName());
        statement.bindLong(2, entity.isKeep());
        statement.bindLong(3, entity.getPosition());
        statement.bindString(4, entity.getName());
        statement.bindLong(5, entity.isKeep());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from skbfun";
        return _query;
      }
    };
  }

  @Override
  public void insert(final SkbFun bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSkbFun.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<? extends SkbFun> bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSkbFun.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final SkbFun bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSkbFun.handle(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final SkbFun bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfSkbFun.handle(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
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
  public List<SkbFun> getAllMenu() {
    final String _sql = "select * from skbfun  where isKeep = 0 ORDER BY position ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIsKeep = CursorUtil.getColumnIndexOrThrow(_cursor, "isKeep");
      final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
      final List<SkbFun> _result = new ArrayList<SkbFun>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final SkbFun _item;
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpIsKeep;
        _tmpIsKeep = _cursor.getInt(_cursorIndexOfIsKeep);
        final int _tmpPosition;
        _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
        _item = new SkbFun(_tmpName,_tmpIsKeep,_tmpPosition);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<SkbFun> getALlBarMenu() {
    final String _sql = "select * from skbfun  where isKeep = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIsKeep = CursorUtil.getColumnIndexOrThrow(_cursor, "isKeep");
      final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
      final List<SkbFun> _result = new ArrayList<SkbFun>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final SkbFun _item;
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpIsKeep;
        _tmpIsKeep = _cursor.getInt(_cursorIndexOfIsKeep);
        final int _tmpPosition;
        _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
        _item = new SkbFun(_tmpName,_tmpIsKeep,_tmpPosition);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public SkbFun getBarMenu(final String name) {
    final String _sql = "select * from skbfun where name = ? AND isKeep = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, name);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIsKeep = CursorUtil.getColumnIndexOrThrow(_cursor, "isKeep");
      final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
      final SkbFun _result;
      if (_cursor.moveToFirst()) {
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpIsKeep;
        _tmpIsKeep = _cursor.getInt(_cursorIndexOfIsKeep);
        final int _tmpPosition;
        _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
        _result = new SkbFun(_tmpName,_tmpIsKeep,_tmpPosition);
      } else {
        _result = null;
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
