package com.peng.setp.service;

import com.peng.setp.bean.ConsultContent;
import rx.Observable;

import java.util.List;
import java.util.concurrent.Future;

public interface UserService {

    List<ConsultContent> queryContents();

    Future<String>queryContentsAsyn();

    List<ConsultContent> queryContent();

    String queryMoniter();

    public Observable<String> mergeResult();



}
