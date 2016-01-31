import {Component} from 'angular2/core';

import {MATERIAL_DIRECTIVES} from 'ng2-material/all';

@Component({
  selector: 'login',
  templateUrl: './components/login/login.html',
  directives: [MATERIAL_DIRECTIVES]
})
export class LoginCmp {
  public login: string = '';
  public password: string = '';

  constructor() {
  }

  /**
   * @returns return false to prevent default form submit behavior to refresh the page.
   */
  login(): boolean {
    this.login = '';
    return false;
  }
}
