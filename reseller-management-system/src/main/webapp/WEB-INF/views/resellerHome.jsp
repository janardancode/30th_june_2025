<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reseller Dashboard</title>

<style>
    :root {
        --z-purple: #5A189A;
        --z-pink:   #FF4B8B;
        --z-dark:   #12001f;
    }

    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    body {
        font-family: "Poppins", sans-serif;
        background: radial-gradient(circle at top, #ff4b8b 0%, #2b0040 45%, #050009 100%);
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        color: #ffffff;
    }

    .dashboard-shell {
        width: 420px;
        max-width: 95vw;
        background: rgba(15, 1, 30, 0.95);
        border-radius: 24px;
        padding: 22px 20px 24px;
        box-shadow: 0 18px 40px rgba(0, 0, 0, 0.7);
        border: 1px solid rgba(255, 255, 255, 0.06);
        backdrop-filter: blur(18px);
        animation: fadeIn 0.5s ease-out;
    }

    @keyframes fadeIn {
        from {opacity: 0; transform: translateY(12px);}
        to   {opacity: 1; transform: translateY(0);}
    }

    .header-row {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 12px;
    }

    .brand-badge {
        display: flex;
        align-items: center;
        gap: 8px;
    }

    .brand-icon {
        width: 34px;
        height: 34px;
        border-radius: 12px;
        background: radial-gradient(circle at 20% 20%, #ff69b4, #ff007a 50%, #7a1cff 100%);
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: 800;
        font-size: 18px;
        color: #fff;
        box-shadow: 0 0 14px rgba(255, 0, 122, 0.8);
    }

    .brand-text-main {
        display: flex;
        flex-direction: column;
    }

    .brand-title {
        font-size: 14px;
        font-weight: 700;
    }

    .brand-sub {
        font-size: 11px;
        opacity: 0.8;
    }

    .status-pill {
        font-size: 11px;
        padding: 4px 10px;
        border-radius: 999px;
        background: rgba(46, 213, 115, 0.15);
        border: 1px solid rgba(46, 213, 115, 0.5);
        color: #a3ffb9;
    }

    .welcome-block {
        margin-top: 6px;
        margin-bottom: 12px;
    }

    .welcome-title {
        font-size: 18px;
        font-weight: 700;
    }

    .welcome-sub {
        font-size: 12px;
        opacity: 0.8;
        margin-top: 2px;
    }

    .info-card {
        margin-top: 10px;
        background: linear-gradient(135deg, rgba(90,24,154,0.6), rgba(255,75,139,0.2));
        border-radius: 16px;
        padding: 14px 14px 12px;
        border: 1px solid rgba(192,132,252,0.5);
    }

    .info-row {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 6px;
    }

    .info-label {
        font-size: 12px;
        opacity: 0.85;
    }

    .info-value {
        font-size: 13px;
        font-weight: 600;
    }

    .section-title {
        margin-top: 16px;
        font-size: 13px;
        font-weight: 600;
        opacity: 0.9;
    }

    .actions-grid {
        margin-top: 10px;
        display: flex;
        flex-direction: column;
        gap: 9px;
    }

    .dash-btn {
        text-decoration: none;
        border: none;
        outline: none;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 11px 13px;
        border-radius: 14px;
        font-size: 14px;
        font-weight: 600;
        cursor: pointer;
        background: #1a0828;
        color: #ffffff;
        box-shadow: 0 10px 22px rgba(0, 0, 0, 0.55);
        transition: transform 0.15s ease, box-shadow 0.15s ease, background 0.15s ease;
    }

    .dash-btn.primary {
        background: linear-gradient(135deg, var(--z-purple), var(--z-pink));
    }

    .dash-btn.logout {
        background: linear-gradient(135deg, #ff5f6d, #ffc371);
    }

    .dash-btn span {
        display: flex;
        align-items: center;
        gap: 8px;
    }

    .dash-icon-pill {
        width: 26px;
        height: 26px;
        border-radius: 999px;
        background: radial-gradient(circle, #ff9a9e, #fad0c4);
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 15px;
    }

    .dash-btn .arrow {
        font-size: 16px;
        opacity: 0.8;
    }

    .dash-btn:hover {
        transform: translateY(-2px);
        box-shadow: 0 14px 26px rgba(0, 0, 0, 0.75);
    }

    .footer-text {
        margin-top: 12px;
        text-align: center;
        font-size: 11px;
        opacity: 0.65;
    }

    @media (max-width: 480px) {
        .dashboard-shell {
            width: 100vw;
            height: 100vh;
            border-radius: 0;
        }
    }
</style>

</head>

<body>
<div class="dashboard-shell">

    <div class="header-row">
        <div class="brand-badge">
            <div class="brand-icon">Z</div>
            <div class="brand-text-main">
                <div class="brand-title">Reseller Dashboard</div>
                <div class="brand-sub">Inventory & stocks at a glance</div>
            </div>
        </div>
        <div class="status-pill">Online • Active</div>
    </div>

    <div class="welcome-block">
        <div class="welcome-title">
            Welcome, ${reseller.firstName} ${reseller.lastName}
        </div>
        <div class="welcome-sub">
            Manage your products, stocks and sessions from one place.
        </div>
    </div>

    <div class="info-card">
        <div class="info-row">
            <div class="info-label">Email</div>
            <div class="info-value">${reseller.email}</div>
        </div>
        <div class="info-row">
            <div class="info-label">Mobile</div>
            <div class="info-value">${reseller.mobile}</div>
        </div>
    </div>

    <div class="section-title">Quick Actions</div>

    <div class="actions-grid">
        <a href="${pageContext.request.contextPath}/stocks" class="dash-btn primary">
            <span>
                <div class="dash-icon-pill">📦</div>
                <div>Manage Stocks</div>
            </span>
            <div class="arrow">›</div>
        </a>

        <a href="${pageContext.request.contextPath}/stocks/new" class="dash-btn">
            <span>
                <div class="dash-icon-pill">➕</div>
                <div>Add New Stock</div>
            </span>
            <div class="arrow">›</div>
        </a>

        <a href="${pageContext.request.contextPath}/logout" class="dash-btn logout">
            <span>
                <div class="dash-icon-pill">🚪</div>
                <div>Logout</div>
            </span>
            <div class="arrow">›</div>
        </a>
    </div>

    <div class="footer-text">
        Tip: Keep your stock list updated for smoother orders ⚡
    </div>

</div>
</body>
</html>
