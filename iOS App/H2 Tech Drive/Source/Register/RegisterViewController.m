//
//  RegisterViewController.m
//  H2 Tech Drive
//
//  Created by Macbook Pro on 23/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import "RegisterViewController.h"
#import "Reachability.h"
#import "SBJson.h"
#import "SVProgressHUD.h"
#import "AFURLConnectionOperation.h"
#import "AFHTTPRequestOperation.h"
#import "AFHTTPRequestOperationManager.h"
#import "AFNetworking.h"

@interface RegisterViewController ()

@end

@implementation RegisterViewController
@synthesize txtEmail, txtFirstName, txtLastName, txtPassword;
@synthesize cmdRegister;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [txtFirstName setReturnKeyType:UIReturnKeyDone];
    [txtFirstName addTarget:self
                     action:@selector(resignFirstResponder)
           forControlEvents:UIControlEventEditingDidEndOnExit];
    
    [txtLastName setReturnKeyType:UIReturnKeyDone];
    [txtLastName addTarget:self
                    action:@selector(resignFirstResponder)
          forControlEvents:UIControlEventEditingDidEndOnExit];
    
    [txtEmail setReturnKeyType:UIReturnKeyDone];
    [txtEmail addTarget:self
                 action:@selector(resignFirstResponder)
       forControlEvents:UIControlEventEditingDidEndOnExit];
    
    [txtPassword setReturnKeyType:UIReturnKeyDone];
    [txtPassword addTarget:self
                    action:@selector(resignFirstResponder)
          forControlEvents:UIControlEventEditingDidEndOnExit];
    
    
    [cmdRegister addTarget:self
                    action:@selector(performRegistration)
          forControlEvents:UIControlEventTouchUpInside];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];

}

#pragma mark - Register Action

-(void)performRegistration{
    
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
            @"firstName": txtFirstName.text,
            @"lastName": txtLastName.text
        };//{@"user" :txtUserName, @"pwd" :txtPwd };
        
        [manager POST:@"http://h2api.herokuapp.com/register" parameters:params success:^(AFHTTPRequestOperation *operation, id responseObject) {
            
            NSString *responseStr = [[NSString alloc] initWithData:responseObject encoding:NSUTF8StringEncoding];
            NSLog(@"Success: %@", responseStr);
            
            SBJsonParser *newParser = [[SBJsonParser alloc]init];
            NSDictionary *dictArray = [newParser objectWithString:responseStr error:nil];
            
            [SVProgressHUD dismiss];
            
        } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
            [SVProgressHUD dismiss];
            NSLog(@"Error: %@", error);
            
        }];
    }
}

@end
