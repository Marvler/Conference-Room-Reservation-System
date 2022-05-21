import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConferenceRoom } from 'src/app/model/ConferenceRoom';
import { Reservation } from 'src/app/model/Reservation';
import { ConferenceRoomService } from 'src/app/service/conferenceRoomService/conference-room.service';
import { LogoutService } from 'src/app/service/logutService/logout.service';
import { ReservationService } from 'src/app/service/reservationService/reservation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  public reservations: Reservation[];
  public editReservation: Reservation;
  public deleteReservation: Reservation;
  public conferenceRoomId: number;
  public conferenceRoomName: string;
  public organizationId: number;


  constructor(
    private reservationService: ReservationService,
    private route: ActivatedRoute,
    private router: Router,
    private conferencRoomService: ConferenceRoomService,
    private logoutService: LogoutService) { }

  ngOnInit() {
    let id = parseInt(this.route.snapshot.paramMap.get('conferenceRoomId'));
    this.conferenceRoomId = id;
    this.getReservations();
    this.conferencRoomService.getConferenceRoom(this.conferenceRoomId).subscribe(
      (response: ConferenceRoom) => {
        this.conferenceRoomName = response.conferenceRoomName
        this.organizationId = response.organizationId
      }

    )
  }

  public getReservations(): void {
    this.reservationService.getReservations(this.conferenceRoomId).subscribe(
      (response: Reservation[]) => {
        this.reservations = response;
        console.log(this.reservations);
      },
      (error: HttpErrorResponse) => {
        alert("Ooops, Something went wrong");
      }
    );
  }

  public onAddReservation(addForm: NgForm): void {
    document.getElementById('add-reservation-room-form')!.click();
    this.reservationService.addReservation(addForm.value, this.conferenceRoomId).subscribe(
      (response: Reservation) => {
        console.log(response);
        this.getReservations();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        if (error.status === 400) {
          alert("Reservation time collides with already existing reservation");
          addForm.reset();
        } else {
          alert("Ooops, Something went wrong")
        }
      }
    );
  }

  public onUpdateReservation(reservation: Reservation): void {
    this.reservationService.udpateReservation(reservation).subscribe(
      (response: Reservation) => {
        console.log(response);
        this.getReservations();
      },
      (error: HttpErrorResponse) => {
        alert("Ooops, Something went wrong");
      }
    );
  }

  public onDeleteReservation(reservationId: number): void {
    this.reservationService.deleteReservation(reservationId).subscribe(
      (response: void) => {
        console.log(response);
        this.getReservations();
      },
      (error: HttpErrorResponse) => {
        alert("Ooops, Something went wrong");
      }
    );
  }

  public searchReservations(key: string): void {
    console.log(key);
    const results: Reservation[] = [];
    for (const reservation of this.reservations) {
      if (reservation.reservationIdentifier.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(reservation);
      }
    }
    this.reservations = results;
    if (key.length === 0) {
      this.getReservations();
    }
  }

  public onOpenModal(reservation: Reservation, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addReservationModal');
    }
    if (mode === 'edit') {
      this.editReservation = reservation;
      button.setAttribute('data-target', '#updateReservationModal');
    }
    if (mode === 'delete') {
      this.deleteReservation = reservation;
      button.setAttribute('data-target', '#deleteReservationModal');
    }
    container!.appendChild(button);
    button.click();
  }

  public goBack() {
    this.router.navigate(['organization', this.organizationId]);
  }

  public logout() {
    this.router.navigate(['login'])
  }

}
