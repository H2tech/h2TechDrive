//
//  TripHistoryViewController.m
//  H2 Tech Drive
//
//  Created by Macbook Pro on 20/06/2015.
//  Copyright (c) 2015 UnitSol. All rights reserved.
//

#import "TripHistoryViewController.h"

@interface TripHistoryViewController (){
    NSArray *arrTripData;
}

@end

@implementation TripHistoryViewController
@synthesize tableViewTripHistory;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    arrTripData = @[@"Trip Name From To 1",
                    @"Trip Name From To 2",
                    @"Trip Name From To 3",
                    @"Trip Name From To 4",
                    @"Trip Name From To 5"];
    
    tableViewTripHistory.dataSource = self;
    tableViewTripHistory.delegate = self;
    [tableViewTripHistory reloadData];

}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];

}

#pragma mark - UITableView Data Source
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [arrTripData count];
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    UITableViewCell *cell = [[UITableViewCell alloc] init];
    
    cell.textLabel.text = [arrTripData objectAtIndex:indexPath.row];
    
    return cell;
}

#pragma mark - UITable Delegates
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    [self performSegueWithIdentifier:@"segueTripDetail"
                              sender:self];
    
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    
}
@end
