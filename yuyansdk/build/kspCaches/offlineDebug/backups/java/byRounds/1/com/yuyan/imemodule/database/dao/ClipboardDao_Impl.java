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
import com.yuyan.imemodule.database.entry.Clipboard;
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
public final class ClipboardDao_Impl implements ClipboardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Clipboard> __insertionAdapterOfClipboard;

  private final EntityDeletionOrUpdateAdapter<Clipboard> __deletionAdapterOfClipboard;

  private final EntityDeletionOrUpdateAdapter<Clipboard> __updateAdapterOfClipboard;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByContent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldest;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllExceptKeep;

  public ClipboardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfClipboard = new EntityInsertionAdapter<Clipboard>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `clipboard` (`content`,`isKeep`,`time`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Clipboard entity) {
        statement.bindString(1, entity.getContent());
        statement.bindLong(2, entity.isKeep());
        statement.bindLong(3, entity.getTime());
      }
    };
    this.__deletionAdapterOfClipboard = new EntityDeletionOrUpdateAdapter<Clipboard>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `clipboard` WHERE `content` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Clipboard entity) {
        statement.bindString(1, entity.getContent());
      }
    };
    this.__updateAdapterOfClipboard = new EntityDeletionOrUpdateAdapter<Clipboard>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `clipboard` SET `content` = ?,`isKeep` = ?,`time` = ? WHERE `content` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Clipboard entity) {
        statement.bindString(1, entity.getContent());
        statement.bindLong(2, entity.isKeep());
        statement.bindLong(3, entity.getTime());
        statement.bindString(4, entity.getContent());
      }
    };
    this.__preparedStmtOfDeleteByContent = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from clipboard where content = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "delete from clipboard";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldest = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM clipboard WHERE content IN ( SELECT content FROM clipboard ORDER BY time ASC LIMIT ?)";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllExceptKeep = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM clipboard WHERE isKeep = 0";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Clipboard bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfClipboard.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<? extends Clipboard> bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfClipboard.insert(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Clipboard bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfClipboard.handle(bean);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Clipboard bean) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfClipboard.handle(bean);
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
  public void deleteOldest(final int overflow) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldest.acquire();
    int _argIndex = 1;
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
  public void deleteAllExceptKeep() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllExceptKeep.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllExceptKeep.release(_stmt);
    }
  }

  @Override
  public List<Clipboard> getAll() {
    final String _sql = "select * from clipboard ORDER BY isKeep DESC, time DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfIsKeep = CursorUtil.getColumnIndexOrThrow(_cursor, "isKeep");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final List<Clipboard> _result = new ArrayList<Clipboard>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Clipboard _item;
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        final int _tmpIsKeep;
        _tmpIsKeep = _cursor.getInt(_cursorIndexOfIsKeep);
        final long _tmpTime;
        _tmpTime = _cursor.getLong(_cursorIndexOfTime);
        _item = new Clipboard(_tmpContent,_tmpIsKeep,_tmpTime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getCount() {
    final String _sql = "SELECT COUNT(*) FROM clipboard";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
