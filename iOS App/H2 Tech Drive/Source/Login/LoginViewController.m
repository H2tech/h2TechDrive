//
//  LoginViewController.m
//  H2 Tech Drive
//
//  Created by Macbook Pro on 23/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import "LoginViewController.h"
#import "Reachability.h"
#import "SBJson.h"
#import "SVProgressHUD.h"
#import "AFURLConnectionOperation.h"
#import "AFHTTPRequestOperation.h"
#import "AFHTTPRequestOperationManager.h"
#import "AFNetworking.h"
#import "TabbarViewController.h"

@interface LoginViewController ()

@end

@implementation LoginViewController
@synthesize txtPassword, txtEmail;
@synthesize cmdLogin;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [txtEmail setReturnKeyType:UIReturnKeyDone];
    [txtEmail addTarget:self
                 action:@selector(resignFirstResponder)
       forControlEvents:UIControlEventEditingDidEndOnExit];
    
    [txtPassword setReturnKeyType:UIReturnKeyDone];
    [txtPassword addTarget:self
                    action:@selector(resignFirstResponder)
          forControlEvents:UIControlEventEditingDidEndOnExit];
    
    [cmdLogin addTarget:self
                    action:@selector(performLogin)
          forControlEvents:UIControlEventTouchUpInside];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

#pragma mark - Register Action

-(void)performLogin{
    
    Reachability *reach = [Reachability reachabilityForInternetConnection];
    NetworkStatus netStatus = [reach currentReachabilityStatus];
    
    if (netStatus == NotReachable) {
        
    }else{
        
        [SVProgressHUD show];
        
        AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
        manager.responseSerializer = [AFHTTPResponseSerializer serializer];
        
        NSDictionary *params = @ {
            @"email": txtEmail.text,
            @"password": txtPassword.text,
        };
        
        [manager POST:@"http://h2api.herokuapp.com/login" parameters:params success:^(AFHTTPRequestOperation *operation, id responseObject) {
            
            NSString *responseStr = [[NSString alloc] initWithData:responseObject encoding:NSUTF8StringEncoding];
            NSLog(@"Success: %@", responseStr);
            
            SBJsonParser *newParser = [[SBJsonParser alloc]init];
            NSDictionary *dictArray = [newParser objectWithString:responseStr error:nil];
            
            [SVProgressHUD dismiss];
            
            UINavigationController *nav = [self.storyboard instantiateViewControllerWithIdentifier:@"sidTabbar"];
            nav.transitioningDelegate = self;
            [self presentViewController:nav animated:YES completion:nil];
            
        } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
            [SVProgressHUD dismiss];
            NSLog(@"Error: %@", error);
            
        }];
    }
}

@end
