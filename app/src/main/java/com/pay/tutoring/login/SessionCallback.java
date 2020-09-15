package com.pay.tutoring.login;

import android.util.Log;

import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;

public class SessionCallback {


//    // 로그인에 성공한 상태
//    @Override
//    public void onSessionOpened() {
//        requestMe();
//    }
//
//    // 로그인에 실패한 상태
//    @Override
//    public void onSessionOpenFailed(KakaoException exception) {
//        Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());
//    }

    // 사용자 정보 요청
    public void requestMe() {
        UserManagement.getInstance()
                .me(new MeV2ResponseCallback() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                    }

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "사용자 정보 요청 실패: " + errorResult);
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        UserAccount kakaoAccount = result.getKakaoAccount();
                        Profile profile = kakaoAccount.getProfile();

                        String user_id;
                        String user_email = "null";
                        String user_age = "null";
                        String user_gender = "null";
                        String user_birthday = "null";
                        String user_name = "null";

                        user_id = Long.toString(result.getId());
                        Log.i("KAKAO_API", "사용자 아이디: " + result.getId());



                        //if-else 문으로 선택 처리
                        if (kakaoAccount.hasEmail() == OptionalBoolean.TRUE) {
                            Log.i("KAKAO_API", "사용자 이메일: " + result.getKakaoAccount().getEmail());
                            user_email = kakaoAccount.getEmail();
                        }

                        if (result.getKakaoAccount().hasAgeRange() == OptionalBoolean.TRUE) {
                            Log.i("KAKAO_API", "사용자 나이대: " + result.getKakaoAccount().getAgeRange().getValue());
                            user_age = kakaoAccount.getAgeRange().getValue();

                        }

                        if (result.getKakaoAccount().hasGender() == OptionalBoolean.TRUE) {
                            Log.i("KAKAO_API", "사용자 성별: " + result.getKakaoAccount().getGender().getValue());
                            user_gender = kakaoAccount.getGender().getValue();

                        }


                        if (result.getKakaoAccount().hasBirthday() == OptionalBoolean.TRUE) {
                            Log.i("KAKAO_API", "사용자 생일: " + result.getKakaoAccount().getBirthday());
                            user_birthday = kakaoAccount.getBirthday();

                        }


                        Log.i("KAKAO_API", "사용자 닉네임: " + result.getKakaoAccount().getProfile().getNickname());
                        user_name = kakaoAccount.getProfile().getNickname();


                    }
                });
    }

    public void kakaoLogOut() {
        UserManagement.getInstance()
                .requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Log.i("KAKAO_API", "로그아웃 완료");
                    }
                });
    }

    private void SessionClose() {
        UserManagement.getInstance()
                .requestUnlink(new UnLinkResponseCallback() {
                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "세션이 닫혀 있음: " + errorResult);
                    }

                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        Log.e("KAKAO_API", "연결 끊기 실패: " + errorResult);

                    }

                    @Override
                    public void onSuccess(Long result) {
                        Log.i("KAKAO_API", "연결 끊기 성공. id: " + result);
                    }
                });
    }




}
