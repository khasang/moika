import { Component, OnInit } from '@angular/core';
import {WashFacility} from '../../model/entities/wash-facility';
import {WashFacilityService} from "../../model/services/wash-facility.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'wash-facilities',
  templateUrl: './wash-facility.component.html',
  styleUrls: ['./wash-facility.component.css']
})

export class WashFacilityComponent implements OnInit {

  washFacilityList: Array<WashFacility> = [];
  selectedFclt: WashFacility;
  private washFacilityService: WashFacilityService;

  constructor(private activateRoute: ActivatedRoute,
    washFacilityService: WashFacilityService)  {
    this.washFacilityService = washFacilityService;
  }

  getAll(): void {
    this.washFacilityService.getAll().then(washFacilityList => this.washFacilityList = washFacilityList).catch(this.handleError);
  }

  ngOnInit(): void {
    console.log("Current route "+this.activateRoute.snapshot.url.toString());
    this.getAll();
  }

  onSelect(washFacility: WashFacility): void {
    this.selectedFclt = washFacility;
  }

  private handleError(error: any): Promise<any> {
    console.error('Не могу получить список моек. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return Promise.reject(error.message || error);
  }

}
