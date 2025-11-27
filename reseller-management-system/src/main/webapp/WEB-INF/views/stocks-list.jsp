<%@ page import="java.util.List" %>
<%@ page import="com.kodewala.model.Stock" %>
<html>
<head>
<title>My Stocks</title>

<style>
:root {
  --z-purple: #5A189A;
  --z-bg: #050009;
  --z-surface: rgba(15,1,30,0.96);
}

body {
  font-family: "Poppins", sans-serif;
  background: radial-gradient(circle at top, #ff4b8b 0%, #2b0040 45%, var(--z-bg) 100%);
  margin: 0;
  padding: 22px 14px;
  color: #fff;
}

.page-shell {
  max-width: 900px;
  margin: auto;
}

h2 {
  font-size: 20px;
  font-weight: 700;
  margin-bottom: 6px;
  text-align: center;
}

.sub {
  font-size: 12px;
  opacity: 0.7;
  margin-bottom: 18px;
  text-align: center;
}

.nav-row {
  display: flex;
  justify-content: center;
  gap: 12px;
  flex-wrap: wrap;
  margin-bottom: 20px;
}

.nav-btn {
  text-decoration: none;
  padding: 8px 14px;
  border-radius: 999px;
  font-size: 13px;
  font-weight: 600;
  background: var(--z-surface);
  border: 1.6px solid rgba(192,132,252,0.5);
  color: #fff;
  transition: 0.18s;
  box-shadow: 0 8px 22px rgba(0,0,0,0.4);
}

.nav-btn:hover {
  transform: translateY(-2px);
  border-color: #ff4b8b;
  box-shadow: 0 14px 28px rgba(0,0,0,0.7);
  background: linear-gradient(135deg, #3f0b8f, #ff007a);
}

.table-wrap {
  background: var(--z-surface);
  padding: 12px;
  border-radius: 18px;
  overflow-x: auto;
  box-shadow: 0 18px 40px rgba(0,0,0,0.7);
  border: 1px solid rgba(255,255,255,0.06);
}

table {
  width: 100%;
  border-collapse: collapse;
  min-width: 520px;
}

table th {
  font-size: 12px;
  font-weight: 600;
  text-transform: uppercase;
  border-bottom: 1.6px solid rgba(148,163,184,0.5);
  padding: 8px;
  text-align: left;
}

table td {
  font-size: 13px;
  padding: 8px;
  border-bottom: 1px solid rgba(30,41,59,0.7);
}

table tbody tr:hover {
  background: rgba(88,28,135,0.35);
}

.tag-id {
  background: rgba(15,23,42,0.9);
  padding: 3px 8px;
  border-radius: 999px;
  font-size: 12px;
  border: 1px solid rgba(148,163,184,0.5);
}

.tag-qty {
  background: rgba(59,130,246,0.16);
  padding: 3px 8px;
  border-radius: 999px;
  font-size: 12px;
  border: 1px solid rgba(59,130,246,0.8);
  color: #bfdbfe;
}

.tag-price {
  background: rgba(16,185,129,0.16);
  padding: 3px 8px;
  border-radius: 999px;
  font-size: 12px;
  border: 1px solid rgba(16,185,129,0.8);
  color: #a7f3d0;
}
</style>

</head>
<body>
<div class="page-shell">

  <h2>Stock List</h2>
  <div class="sub">Review, edit or delete stocks quickly</div>

  <div class="nav-row">
    <a class="nav-btn" href="${pageContext.request.contextPath}/stocks/new">Add New Stock</a>
    <a class="nav-btn" href="${pageContext.request.contextPath}/home">Dashboard</a>
    <a class="nav-btn" href="${pageContext.request.contextPath}/stocks/new">New Stock</a>
    <a class="nav-btn" href="${pageContext.request.contextPath}/logout">Logout</a>
  </div>

  <%
      List<Stock> stocks = (List<Stock>) request.getAttribute("stocks");
      if (stocks == null || stocks.isEmpty()) {
  %>
      <p class="sub" style="margin-top:14px;">No stocks found</p>
  <%
      } else {
  %>
      <div class="table-wrap">
          <table>
              <thead>
                  <tr>
                      <th>ID</th>
                      <th>Product</th>
                      <th>Quantity</th>
                      <th>Price</th>
                      <th style="text-align:right;">Actions</th>
                  </tr>
              </thead>
              <tbody>
  <%
                  for (Stock s : stocks) {
  %>
                  <tr>
                      <td><span class="tag-id">#<%= s.getId() %></span></td>
                      <td><%= s.getProductName() %></td>
                      <td><span class="tag-qty"><%= s.getQuantity() %></span></td>
                      <td><span class="tag-id"><%= s.getPrice() %></span></td>
                      <td>
                          <div class="actions" style="justify-content:flex-end;">
                              <a class="nav-btn" href="<%= request.getContextPath() %>/stocks/edit?id=<%= s.getId() %>">Edit</a>
                              <a class="nav-btn" href="<%= request.getContextPath() %>/stocks/delete?id=<%= s.getId() %>"
                                 onclick="return confirm('Delete this stock?');">Delete</a>
                          </div>
                      </td>
                  </tr>
  <%
                  }
  %>
              </tbody>
          </table>
          </div>
      </div>
  <%
      }
  %>

</div>
</body>
</html>
