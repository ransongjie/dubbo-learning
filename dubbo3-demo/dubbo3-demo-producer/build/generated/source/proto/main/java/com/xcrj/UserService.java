//package com.xcrj;
//
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public interface UserService {
//
//    static final String JAVA_SERVICE_NAME = "com.xcrj.UserService";
//    static final String SERVICE_NAME = "api.UserService";
//
//    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
//    static final boolean inited = UserServiceDubbo.init();
//
//        /**
//         * <pre>
//         * rpc方法 方法名 (入参) returns (出参)
//         * </pre>
//         */
//    com.xcrj.User getUser(com.xcrj.UserRequest request);
//
//    default CompletableFuture<com.xcrj.User> getUserAsync(com.xcrj.UserRequest request){
//        return CompletableFuture.supplyAsync(() -> getUser(request));
//    }
//
//
//
//
//}
