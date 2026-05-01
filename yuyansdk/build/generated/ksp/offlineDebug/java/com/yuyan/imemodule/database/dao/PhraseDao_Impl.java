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
import com.yuyan.imemodule.database.entry.Phrase;
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
public final class PhraseDao_Impl implements PhraseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Phrase> __insertionAdapterOfPhrase;

  private final EntityDeletionOrUpdateAdapter<Phrase> __deletionAdapterOfPhrase;

  private final EntityDeletionOrUpdateAdapter<Phrase> __updateAdapterOfPhrase;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByContent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public PhraseDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPhrase = new EntityInsertionAdapter<Phrase>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `phrase` (`content`,`isKeep`,`t9`,`qwerty`,`lx17`,`time`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Phrase entity) {
        statement.bindString(1, entity.getContent());
        statement.bindLong(2, entity.isKeep());
        statement.bindString(3, entity.getT9());
        statement.bindString(4, entity.getQwerty());
        statement.bindString(5, entity.getLx17());
        statement.bindLong(6, entity.getTime());
      }
    };
    this.__deletionAdapterOfPhrase = new EntityDeletionOrUpdateAdapter<Phrase>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `phrase` WHERE `content` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Phrase entity) {
        statement.bindString(1, entity.getContent());
      }
    };
    this.__updateAdapterOfPhrase = new EntityDeletionOrUpdateAdapter<Phrase>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `phrase` SET `content` = ?,`isKeep` = ?,`t9` = ?,`qwerty` = ?,`lx17` = ?,`time` = ? WHERE `content` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Phrase entity) {
        statement.bindString(1, entity.getContent());
        statement.bindLong(2, entity.isKeep());
        statement.bindString(3, entity.getT9());
        statement.bindString(4, entity.getQwerty());
        statement.bindString(5, entity.getLx17());
        statement.bindLong(6, entity.getTime());
        statement.bindString(7, entity.getContent());
      }
    };
    this.__preparedStmtOfDeleteByContent = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from phrase where content = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from phrase";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Phrase bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPhrase.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<? extends Phrase> bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPhrase.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Phrase bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPhrase.handle(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Phrase bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPhrase.handle(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteByContent(final String content) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByContent.acquire();
    int _argIndex = 1;
    _stmt.bindString(_argIndex, content);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteByContent.release(_stmt);
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
  public List<Phrase> getAll() {
    final String _sql = "select * from phrase ORDER BY isKeep DESC, time DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfIsKeep = CursorUtil.getColumnIndexOrThrow(_cursor, "isKeep");
      final int _cursorIndexOfT9 = CursorUtil.getColumnIndexOrThrow(_cursor, "t9");
      final int _cursorIndexOfQwerty = CursorUtil.getColumnIndexOrThrow(_cursor, "qwerty");
      final int _cursorIndexOfLx17 = CursorUtil.getColumnIndexOrThrow(_cursor, "lx17");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final List<Phrase> _result = new ArrayList<Phrase>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Phrase _item;
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        final int _tmpIsKeep;
        _tmpIsKeep = _cursor.getInt(_cursorIndexOfIsKeep);
        final String _tmpT9;
        _tmpT9 = _cursor.getString(_cursorIndexOfT9);
        final String _tmpQwerty;
        _tmpQwerty = _cursor.getString(_cursorIndexOfQwerty);
        final String _tmpLx17;
        _tmpLx17 = _cursor.getString(_cursorIndexOfLx17);
        final long _tmpTime;
        _tmpTime = _cursor.getLong(_cursorIndexOfTime);
        _item = new Phrase(_tmpContent,_tmpIsKeep,_tmpT9,_tmpQwerty,_tmpLx17,_tmpTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Phrase> query(final String index) {
    final String _sql = "select * from phrase  where qwerty = ? or t9 = ? or lx17 = ? ORDER BY isKeep DESC, time DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, index);
    _argIndex = 2;
    _statement.bindString(_argIndex, index);
    _argIndex = 3;
    _statement.bindString(_argIndex, index);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfIsKeep = CursorUtil.getColumnIndexOrThrow(_cursor, "isKeep");
      final int _cursorIndexOfT9 = CursorUtil.getColumnIndexOrThrow(_cursor, "t9");
      final int _cursorIndexOfQwerty = CursorUtil.getColumnIndexOrThrow(_cursor, "qwerty");
      final int _cursorIndexOfLx17 = CursorUtil.getColumnIndexOrThrow(_cursor, "lx17");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final List<Phrase> _result = new ArrayList<Phrase>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Phrase _item;
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        final int _tmpIsKeep;
        _tmpIsKeep = _cursor.getInt(_cursorIndexOfIsKeep);
        final String _tmpT9;
        _tmpT9 = _cursor.getString(_cursorIndexOfT9);
        final String _tmpQwerty;
        _tmpQwerty = _cursor.getString(_cursorIndexOfQwerty);
        final String _tmpLx17;
        _tmpLx17 = _cursor.getString(_cursorIndexOfLx17);
        final long _tmpTime;
        _tmpTime = _cursor.getLong(_cursorIndexOfTime);
        _item = new Phrase(_tmpContent,_tmpIsKeep,_tmpT9,_tmpQwerty,_tmpLx17,_tmpTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Phrase queryByContent(final String content) {
    final String _sql = "select * from phrase where content = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, content);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfIsKeep = CursorUtil.getColumnIndexOrThrow(_cursor, "isKeep");
      final int _cursorIndexOfT9 = CursorUtil.getColumnIndexOrThrow(_cursor, "t9");
      final int _cursorIndexOfQwerty = CursorUtil.getColumnIndexOrThrow(_cursor, "qwerty");
      final int _cursorIndexOfLx17 = CursorUtil.getColumnIndexOrThrow(_cursor, "lx17");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final Phrase _result;
      if (_cursor.moveToFirst()) {
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        final int _tmpIsKeep;
        _tmpIsKeep = _cursor.getInt(_cursorIndexOfIsKeep);
        final String _tmpT9;
        _tmpT9 = _cursor.getString(_cursorIndexOfT9);
        final String _tmpQwerty;
        _tmpQwerty = _cursor.getString(_cursorIndexOfQwerty);
        final String _tmpLx17;
        _tmpLx17 = _cursor.getString(_cursorIndexOfLx17);
        final long _tmpTime;
        _tmpTime = _cursor.getLong(_cursorIndexOfTime);
        _result = new Phrase(_tmpContent,_tmpIsKeep,_tmpT9,_tmpQwerty,_tmpLx17,_tmpTime);
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
