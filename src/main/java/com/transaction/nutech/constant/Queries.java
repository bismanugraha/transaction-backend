package com.transaction.nutech.constant;

public class Queries {
    // User
    public static final String REGISTER_USER = "INSERT INTO user (email, first_name, last_name, password) VALUES (:email, :first_name, :last_name, :password)";
    public static final String CHECK_USER = "SELECT email, password FROM user WHERE email = :email";
    public static final String FIND_USER_BY_EMAIL = "SELECT id, email, first_name, last_name, profile_image FROM user WHERE email = :email";
    public static final String UPDATE_USER = "UPDATE user SET first_name = :first_name, last_name = :last_name WHERE email = :email";
    public static final String UPLOAD_IMAGE = "UPDATE user SET profile_image = :image WHERE email = :email";
    public static final String GET_USER_BALANCE = "SELECT balance FROM user WHERE email = :email";
    public static final String CHANGE_BALANCE = "UPDATE user SET balance = :balance WHERE email = :email";

    // Banner
    public static final String GET_ALL_BANNER = "SELECT banner_image, banner_name, description FROM banner";

    // Services
    public static final String GET_ALL_SERVICES = "SELECT service_code, service_icon, service_name, service_tariff FROM services";

    // Transaction
    public static final String TRANSACTION = "INSERT INTO transaksi (transaction_type, invoice_number, description, total_amount, created_on, user_email) VALUES (:transaction_type, :invoice_number, :description, :total_amount, :created_on, :user_email)";
    public static final String COUNT_TRANSACTION_HISTORY = "SELECT COUNT(id) AS total FROM transaksi";
    public static final String GET_ALL_TRANSACTION_HISTORY = "SELECT transaction_type, invoice_number, description, total_amount, created_on, user_email FROM transaksi WHERE user_email = :user_email ORDER BY id";
    public static final String LIMIT_AND_OFFSET = " LIMIT :limit OFFSET :offset";

}
