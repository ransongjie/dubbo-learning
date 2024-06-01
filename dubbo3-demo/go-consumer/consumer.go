package main

import (
	"context"
	"dubbo.apache.org/dubbo-go/v3/common/logger"
	"dubbo.apache.org/dubbo-go/v3/config"
	_ "dubbo.apache.org/dubbo-go/v3/imports"
	"go-consumer/api"
)

var userServiceImpl=new(api.UserServiceClientImpl)

func main(){
	//配置
	config.SetConsumerService(userServiceImpl)
	config.Load()

	logger.Info("start to test dubbo")

	//请求 响应
	req:=&api.UserRequest{
		Uid:"1",
	}
	user, err := userServiceImpl.GetUser(context.Background(), req)

	if err!=nil{
		logger.Error(err)
	}

	logger.Infof("client response result: %v\n", user)
}