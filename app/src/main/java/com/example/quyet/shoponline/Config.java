package com.example.quyet.shoponline;

/**
 * Created by Quyet on 10/4/2016.
 */

public class Config {
    //Address of our scripts of the CRUD
    public static final String URL_GET_ALL = "http://192.168.43.20/www/shoponline.com/sanpham/getAllSanPham.php";
    public static final String URL_GET_PRODUCT = "http://192.168.43.20/www/shoponline.com/sanpham/getSanPham.php?id=";
    public static final String URL_GET_COMMENTS = "http://192.168.43.20/www/shoponline.com/comments/getAllComments.php?id_hang=";
    public static final String URL_GET_LIKE = "http://192.168.43.20/www/shoponline.com/likes/getLikes.php?id_hang=";
    public static final String URL_GET_QUAN_AO = "http://192.168.43.20/www/shoponline.com/sanpham/getAllQuanAo.php";
    public static final String URL_GET_GIAY = "http://192.168.43.20/www/shoponline.com/sanpham/getAllGiay.php";
    public static final String URL_GET_DO_THE_THAO = "http://192.168.43.20/www/shoponline.com/sanpham/getAllDoTheThao.php";
    public static final String URL_GET_TUI_VA_PHU_KIEN = "http://192.168.43.20/www/shoponline.com/sanpham/getAllTuiVaPhuKien.php";
    public static final String URL_GET_USER = "http://192.168.43.20/www/shoponline.com/users/getUser.php?taikhoan=";

    public static final String URL_POST_DON_HANG = "http://192.168.43.20/www/shoponline.com/dathang/addDonHang.php";
    public static final String URL_POST_LIKE = "http://192.168.43.20/www/shoponline.com/likes/addLikes.php";
    public static final String URL_POST_BINH_LUAN = "http://192.168.43.20/www/shoponline.com/comments/addComments.php";

    public static final String URL_DELETE_LIKE = "http://192.168.43.20/www/shoponline.com/likes/deleteLikes.php?id=";

    public static final String URL_DEFAULT_AVARTA = "http://192.168.43.20/www/shoponline.com/image/avarta/default_avatar.png";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_PRODUCT_ID = "id";
    public static final String KEY_PRODUCT_TEN = "ten";
    public static final String KEY_PRODUCT_DANH_MUC = "danhmuc";
    public static final String KEY_PRODUCT_THUONG_HIEU = "thuonghieu";
    public static final String KEY_PRODUCT_MAU_SAC = "mausac";
    public static final String KEY_PRODUCT_CHAT_LIEU = "chatlieu";
    public static final String KEY_PRODUCT_SIZE= "size";
    public static final String KEY_PRODUCT_GIOI_THIEU = "gioithieu";
    public static final String KEY_PRODUCT_GIA_CA = "giaca";
    public static final String KEY_PRODUCT_LINK_ANH = "linkanh";
    public static final String KEY_PRODUCT_LUOT_THICH = "soluotthich";
    public static final String KEY_PRODUCT_LUOT_DANH_GIA = "soluotdanhgia";

    public static final String KEY_USER_ID = "id";
    public static final String KEY_USER_TAI_KHOAN = "taikhoan";
    public static final String KEY_USER_MAT_KHAU = "matkhau";
    public static final String KEY_USER_TEN_NGUOI_DUNG = "tennguoidung";
    public static final String KEY_USER_GIOI_TINH = "gioitinh";
    public static final String KEY_USER_NGAY_SINH = "ngaysinh";
    public static final String KEY_USER_SO_DIEN_THOAI = "sodienthoai";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_DIA_CHI = "diachi";
    public static final String KEY_USER_LINK_ANH = "linkanh";

    public static final String KEY_DON_DAT_HANG_ID = "id";
    public static final String KEY_DON_DAT_HANG_ID_HANG = "id_hang";
    public static final String KEY_DON_DAT_HANG_SO_LUONG = "soluong";
    public static final String KEY_DON_DAT_HANG_TEN_KHACH_HANG = "tenkhachhang";
    public static final String KEY_DON_DAT_HANG_SO_DIEN_THOAI = "sodienthoai";
    public static final String KEY_DON_DAT_HANG_DIA_CHI_KHACH_HANG = "diachikhachhang";

    public static final String KEY_LOAI_HANG_ID = "id";

    public static final String KEY_COMMENT_ID = "id";
    public static final String KEY_COMMENT_ID_NGUOI_DUNG = "id_nguoi_dung";
    public static final String KEY_COMMENT_TEN_NGUOI_DUNG = "tennguoidung";
    public static final String KEY_COMMENT_LINK_ANH = "linkanh";
    public static final String KEY_COMMENT_ID_HANG = "id_hang";
    public static final String KEY_COMMENT_NOI_DUNG = "noidung";

    public static final String KEY_LIKE_ID = "id";
    public static final String KEY_LIKE_ID_NGUOI_DUNG = "id_nguoi_dung";
    public static final String KEY_LIKE_ID_HANG = "id_hang";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_PRODUCT_ID = "id";
    public static final String TAG_PRODUCT_TEN = "ten";
    public static final String TAG_PRODUCT_DANH_MUC = "danhmuc";
    public static final String TAG_PRODUCT_THUONG_HIEU = "thuonghieu";
    public static final String TAG_PRODUCT_MAU_SAC = "mausac";
    public static final String TAG_PRODUCT_CHAT_LIEU = "chatlieu";
    public static final String TAG_PRODUCT_SIZE= "size";
    public static final String TAG_PRODUCT_GIOI_THIEU = "gioithieu";
    public static final String TAG_PRODUCT_GIA_CA = "giaca";
    public static final String TAG_PRODUCT_LINK_ANH = "linkanh";
    public static final String TAG_PRODUCT_LUOT_THICH = "soluotthich";
    public static final String TAG_PRODUCT_LUOT_DANH_GIA = "soluotdanhgia";

    public static final String TAG_USER_ID = "id";
    public static final String TAG_USER_TAI_KHOAN = "taikhoan";
    public static final String TAG_USER_MAT_KHAU = "matkhau";
    public static final String TAG_USER_TEN_NGUOI_DUNG = "tennguoidung";
    public static final String TAG_USER_GIOI_TINH = "gioitinh";
    public static final String TAG_USER_NGAY_SINH = "ngaysinh";
    public static final String TAG_USER_SO_DIEN_THOAI = "sodienthoai";
    public static final String TAG_USER_EMAIL = "email";
    public static final String TAG_USER_DIA_CHI = "diachi";
    public static final String TAG_USER_LINK_ANH = "linkanh";

    public static final String TAG_DON_DAT_HANG_ID = "id";
    public static final String TAG_DON_DAT_HANG_ID_HANG = "id_hang";
    public static final String TAG_DON_DAT_HANG_SO_LUONG = "soluong";
    public static final String TAG_DON_DAT_HANG_TEN_KHACH_HANG = "tenkhachhang";
    public static final String TAG_DON_DAT_HANG_SO_DIEN_THOAI = "sodienthoai";
    public static final String TAG_DON_DAT_HANG_DIA_CHI_KHACH_HANG = "diachikhachhang";

    public static final String TAG_COMMENT_ID = "id";
    public static final String TAG_COMMENT_ID_NGUOI_DUNG = "id_nguoi_dung";
    public static final String TAG_COMMENT_TEN_NGUOI_DUNG = "tennguoidung";
    public static final String TAG_COMMENT_LINK_ANH = "linkanh";
    public static final String TAG_COMMENT_ID_HANG = "id_hang";
    public static final String TAG_COMMENT_NOI_DUNG = "noidung";

    public static final String TAG_LIKE_ID = "id";
    public static final String TAG_LIKE_ID_NGUOI_DUNG = "id_nguoi_dung";
    public static final String TAG_LIKE_ID_HANG = "id_hang";

    //product id to pass with intent
    public static final String EMP_ID = "emp_id";
    public static final int LOGIN_REQUEST = 69;
    public static final int RESULT_CODE_SUCCESS = 69;

    //
    public static final String SHARE_PREFRENCES_USERS = "user";
    public static final String SHARE_PREFRENCES_LOAI_HANG = "loaihang";

}