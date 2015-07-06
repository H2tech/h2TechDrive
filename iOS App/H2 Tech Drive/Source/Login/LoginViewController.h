//
//  LoginViewController.h
//  H2 Tech Drive
//
//  Created by Macbook Pro on 23/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LoginViewController : UIViewController

@property (weak, nonatomic) IBOutlet UITextField *txtEmail;
@property (weak, nonatomic) IBOutlet UITextField *txtPassword;

@property (weak, nonatomic) IBOutlet UIButton *cmdLogin;
@end
