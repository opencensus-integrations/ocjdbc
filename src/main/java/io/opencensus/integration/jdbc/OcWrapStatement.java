// Copyright 2018, OpenCensus Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package io.opencensus.integration.jdbc;

import io.opencensus.common.Scope;
import io.opencensus.integration.jdbc.Observability.TraceOption;
import io.opencensus.integration.jdbc.Observability.TrackingOperation;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EnumSet;

/** Wraps and instruments a {@link Statement} instance with tracing and metrics using OpenCensus. */
public class OcWrapStatement implements Statement {
  private final Statement statement;
  private final boolean shouldAnnotateSpansWithSQL;

  public OcWrapStatement(Statement stmt, EnumSet<TraceOption> opts) {
    this.statement = stmt;
    this.shouldAnnotateSpansWithSQL = Observability.shouldAnnotateSpansWithSQL(opts);
  }

  @Override
  public void addBatch(String SQL) throws SQLException {
    this.statement.addBatch(SQL);
  }

  @Override
  public void cancel() throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("java.sql.Statement.cancel");

    try (Scope ws = trackingOperation.withSpan()) {
      this.statement.cancel();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public void clearBatch() throws SQLException {
    this.statement.clearBatch();
  }

  @Override
  public void clearWarnings() throws SQLException {
    this.statement.clearWarnings();
  }

  @Override
  public void close() throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("java.sql.Statement.close");

    try (Scope ws = trackingOperation.withSpan()) {
      this.statement.close();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public void closeOnCompletion() throws SQLException {
    this.statement.closeOnCompletion();
  }

  @Override
  public boolean execute(String SQL) throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "java.sql.Statement.execute", this.shouldAnnotateSpansWithSQL, SQL);

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.execute(SQL);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public boolean execute(String SQL, int autoGeneratedKeys) throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "java.sql.Statement.execute", this.shouldAnnotateSpansWithSQL, SQL);

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.execute(SQL, autoGeneratedKeys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public boolean execute(String SQL, int[] columnIndices) throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "java.sql.Statement.execute", this.shouldAnnotateSpansWithSQL, SQL);

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.execute(SQL, columnIndices);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public boolean execute(String SQL, String[] columnNames) throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "java.sql.Statement.execute", this.shouldAnnotateSpansWithSQL, SQL);

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.execute(SQL, columnNames);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public int[] executeBatch() throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("java.sql.Statement.executeBatch");

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.executeBatch();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public java.sql.ResultSet executeQuery(String SQL) throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "java.sql.Statement.executeQuery", this.shouldAnnotateSpansWithSQL, SQL);

    try (Scope ws = trackingOperation.withSpan()) {
      java.sql.ResultSet rs = this.statement.executeQuery(SQL);
      return new OcWrapResultSet(rs);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public int executeUpdate(String SQL) throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "java.sql.Statement.executeUpdate", this.shouldAnnotateSpansWithSQL, SQL);

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.executeUpdate(SQL);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public int executeUpdate(String SQL, int autoGeneratedKeys) throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "java.sql.Statement.executeUpdate", this.shouldAnnotateSpansWithSQL, SQL);

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.executeUpdate(SQL, autoGeneratedKeys);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public int executeUpdate(String SQL, int[] columnIndices) throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "java.sql.Statement.executeUpdate", this.shouldAnnotateSpansWithSQL, SQL);

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.executeUpdate(SQL, columnIndices);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public int executeUpdate(String SQL, String[] columnNames) throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan(
            "java.sql.Statement.executeUpdate", this.shouldAnnotateSpansWithSQL, SQL);

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.executeUpdate(SQL, columnNames);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public java.sql.Connection getConnection() throws SQLException {
    return this.statement.getConnection();
  }

  @Override
  public int getFetchDirection() throws SQLException {
    return this.statement.getFetchDirection();
  }

  @Override
  public int getFetchSize() throws SQLException {
    return this.statement.getFetchSize();
  }

  @Override
  public java.sql.ResultSet getGeneratedKeys() throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("java.sql.Statement.getGeneratedKeys");

    try (Scope ws = trackingOperation.withSpan()) {
      java.sql.ResultSet rs = this.statement.getGeneratedKeys();
      return new OcWrapResultSet(rs);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public int getMaxFieldSize() throws SQLException {
    return this.statement.getMaxFieldSize();
  }

  @Override
  public int getMaxRows() throws SQLException {
    return this.statement.getMaxRows();
  }

  @Override
  public boolean getMoreResults(int current) throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("java.sql.Statement.getMoreResults");

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.getMoreResults(current);
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public boolean getMoreResults() throws SQLException {
    TrackingOperation trackingOperation =
        Observability.createRoundtripTrackingSpan("java.sql.Statement.getMoreResults");

    try (Scope ws = trackingOperation.withSpan()) {
      return this.statement.getMoreResults();
    } catch (Exception e) {
      trackingOperation.recordException(e);
      throw e;
    } finally {
      trackingOperation.end();
    }
  }

  @Override
  public int getQueryTimeout() throws SQLException {
    return this.statement.getQueryTimeout();
  }

  @Override
  public java.sql.ResultSet getResultSet() throws SQLException {
    java.sql.ResultSet rs = this.statement.getResultSet();
    return new OcWrapResultSet(rs);
  }

  @Override
  public int getResultSetConcurrency() throws SQLException {
    return this.statement.getResultSetConcurrency();
  }

  @Override
  public int getResultSetHoldability() throws SQLException {
    return this.statement.getResultSetHoldability();
  }

  @Override
  public int getResultSetType() throws SQLException {
    return this.statement.getResultSetType();
  }

  @Override
  public int getUpdateCount() throws SQLException {
    return this.statement.getUpdateCount();
  }

  @Override
  public java.sql.SQLWarning getWarnings() throws SQLException {
    return this.statement.getWarnings();
  }

  @Override
  public boolean isClosed() throws SQLException {
    return this.statement.isClosed();
  }

  @Override
  public boolean isCloseOnCompletion() throws SQLException {
    return this.statement.isCloseOnCompletion();
  }

  @Override
  public boolean isPoolable() throws SQLException {
    return this.statement.isPoolable();
  }

  @Override
  public void setCursorName(String cursorName) throws SQLException {
    this.statement.setCursorName(cursorName);
  }

  @Override
  public void setEscapeProcessing(boolean enable) throws SQLException {
    this.statement.setEscapeProcessing(enable);
  }

  @Override
  public void setFetchDirection(int direction) throws SQLException {
    this.statement.setFetchDirection(direction);
  }

  @Override
  public void setFetchSize(int rows) throws SQLException {
    this.statement.setFetchSize(rows);
  }

  @Override
  public void setMaxFieldSize(int max) throws SQLException {
    this.statement.setMaxFieldSize(max);
  }

  @Override
  public void setMaxRows(int max) throws SQLException {
    this.statement.setMaxRows(max);
  }

  @Override
  public void setPoolable(boolean poolable) throws SQLException {
    this.statement.setPoolable(poolable);
  }

  @Override
  public void setQueryTimeout(int seconds) throws SQLException {
    this.statement.setQueryTimeout(seconds);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return this.statement.isWrapperFor(iface);
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return this.statement.unwrap(iface);
  }
}
