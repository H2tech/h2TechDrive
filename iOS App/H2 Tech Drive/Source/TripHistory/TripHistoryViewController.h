//
//  TripHistoryViewController.h
//  H2 Tech Drive
//
//  Created by Macbook Pro on 20/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface TripHistoryViewController : UIViewController<UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableViewTripHistory;
@end
