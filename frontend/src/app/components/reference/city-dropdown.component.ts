import {Component, Input, OnInit} from '@angular/core';
import {City} from "../../model/entities/city";
import {SelectItem} from "primeng/primeng";
import {CrudService} from "../../model/services/crud.service";

@Component({
  selector: 'app-city-dropdown',
  templateUrl: './city-dropdown.component.html',
  styleUrls: ['./city-dropdown.component.css']
})
export class CityDropdownComponent implements OnInit {
  @Input() curCity: string;

  private cityItemList: SelectItem[];
  private cityList : City[];
  selectedCity: City;

  private cityListUrl = 'http://localhost:8080/api/washAddr/city/' ;

  constructor(private dbService: CrudService<City>){
    this.dbService.workUrl = this.cityListUrl ;
  }

  getAll(): void {
    this.dbService.getAll()
      .then(cityList => {
        this.cityList = cityList;
        this.cityItemList = this.cityList.map(this.makeDropDownItem);
      })
      .catch(error => {
        this.handleError(<any>error);
        let errMsg = `Не могу получить список из БД. Error code: ${error.status}, URL: ${error.url}`;
        alert(errMsg);
      });
  }


  makeDropDownItem(element): SelectItem {
    return{label: element.name, value:element};
  }

  ngOnInit(): void {
    this.getAll();
  }

  private handleError(error: any): String {
    console.error('Не могу получить список из БД. Error code: %s, URL: %s ', error.status, error.url); // for demo purposes only
    return error.message || error;
  }


}
