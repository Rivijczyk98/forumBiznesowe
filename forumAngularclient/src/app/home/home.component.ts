import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../_services/category.service';
import {Category} from '../_model/category';
import {MatDialog} from '@angular/material/dialog';
import {CategoryAddComponent} from '../category-add/category-add.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  categories: Category[] = [];

  constructor(
    private categoryService: CategoryService
  ) {
  }

  ngOnInit() {
    this.refreshData();
  }

  private refreshData() {
    this.categoryService.findAll().subscribe(data => {
      this.categories = data;
    });
  }


}
