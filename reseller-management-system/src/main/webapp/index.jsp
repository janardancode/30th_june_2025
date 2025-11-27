<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>

<style>
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

    .app-shell {
        width: 380px;
        max-width: 95vw;
        background: rgba(10, 0, 25, 0.9);
        border-radius: 26px;
        padding: 22px 20px 26px;
        box-shadow: 0 18px 45px rgba(0, 0, 0, 0.6);
        border: 1px solid rgba(255, 255, 255, 0.06);
        backdrop-filter: blur(18px);
    }

    .status-bar {
        display: flex;
        justify-content: space-between;
        font-size: 11px;
        opacity: 0.75;
        margin-bottom: 10px;
    }

    .logo-row {
        display: flex;
        align-items: center;
        gap: 10px;
        margin-bottom: 18px;
    }

    .brand-icon {
        width: 38px;
        height: 38px;
        border-radius: 14px;
        background: radial-gradient(circle at 20% 20%, #ff69b4, #ff007a 45%, #7a1cff 100%);
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: 800;
        font-size: 20px;
        color: #fff;
        box-shadow: 0 0 16px rgba(255, 0, 122, 0.8);
    }

    .brand-text {
        display: flex;
        flex-direction: column;
    }

    .brand-title {
        font-size: 16px;
        font-weight: 700;
        letter-spacing: 0.5px;
    }

    .brand-sub {
        font-size: 11px;
        opacity: 0.8;
    }

    .hero-card {
        margin-top: 8px;
        background: linear-gradient(135deg, #3f0b8f, #ff007a);
        border-radius: 20px;
        padding: 18px 16px;
        color: #fff;
        box-shadow: 0 12px 25px rgba(0, 0, 0, 0.4);
    }

    .hero-title {
        font-size: 17px;
        font-weight: 700;
        margin-bottom: 4px;
    }

    .hero-sub {
        font-size: 12px;
        opacity: 0.9;
    }

    .chips-row {
        margin-top: 14px;
        display: flex;
        gap: 8px;
        flex-wrap: wrap;
    }

    .chip {
        font-size: 11px;
        padding: 5px 9px;
        border-radius: 999px;
        border: 1px solid rgba(255, 255, 255, 0.35);
        background: rgba(0, 0, 0, 0.15);
        backdrop-filter: blur(6px);
    }

    .main-section-title {
        margin-top: 20px;
        font-size: 14px;
        font-weight: 600;
        opacity: 0.9;
    }

    .btn-group {
        margin-top: 10px;
        display: flex;
        flex-direction: column;
        gap: 10px;
    }

    .app-btn {
        border: none;
        outline: none;
        text-decoration: none;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 12px 14px;
        border-radius: 14px;
        font-size: 14px;
        font-weight: 600;
        cursor: pointer;
        background: #2b064a;
        color: #ffffff;
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.5);
        transition: transform 0.15s ease, box-shadow 0.15s ease, background 0.15s ease;
    }

    .app-btn span {
        display: flex;
        align-items: center;
        gap: 8px;
    }

    .app-btn .icon-pill {
        width: 26px;
        height: 26px;
        border-radius: 999px;
        background: radial-gradient(circle at 30% 30%, #ff69b4, #ff007a 60%, #ffb347 100%);
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
    }

    .app-btn .arrow {
        font-size: 16px;
        opacity: 0.8;
    }

    .app-btn.primary {
        background: linear-gradient(135deg, #ff007a, #ff4b8b);
    }

    .app-btn:hover {
        transform: translateY(-2px);
        box-shadow: 0 12px 26px rgba(0, 0, 0, 0.7);
    }

    .footer-note {
        margin-top: 14px;
        text-align: center;
        font-size: 11px;
        opacity: 0.6;
    }

    @media (max-width: 420px) {
        .app-shell {
            border-radius: 0;
            width: 100vw;
            height: 100vh;
            max-width: 100vw;
        }
    }
</style>

</head>
<body>

<div class="app-shell">

    <div class="status-bar">
        <div>Reseller</div>
        <div>Online • Fast access</div>
    </div>

    <div class="logo-row">
        <div class="brand-icon">Z</div>
        <div class="brand-text">
            <div class="brand-title">Reseller Stocks</div>
            <div class="brand-sub">Manage inventory like a pro ⚡</div>
        </div>
    </div>

    <div class="hero-card">
        <div class="hero-title">Welcome to Stock Dashboard</div>
        <div class="hero-sub">
            Track your reseller stock, update quantities, and stay on top of every order.
        </div>
        <div class="chips-row">
            <div class="chip">Live stock view</div>
            <div class="chip">Fast updates</div>
            <div class="chip">Simple UI</div>
        </div>
    </div>

    <div class="main-section-title">Get Started</div>

    <div class="btn-group">
        <a href="register" class="app-btn primary">
            <span>
                <div class="icon-pill">+</div>
                <div>New Reseller Registration</div>
            </span>
            <div class="arrow">›</div>
        </a>

        <a href="login" class="app-btn">
            <span>
                <div class="icon-pill">✓</div>
                <div>Login as Reseller</div>
            </span>
            <div class="arrow">›</div>
        </a>
    </div>

    <div class="footer-note">
        Tip: Save this page to home screen for app-like feel 📱
    </div>

</div>

</body>
</html>
