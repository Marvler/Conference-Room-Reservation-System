export class Reservation{

    reservationIdentifier:string;
    reservationStart: Date;
    reservationEnd: Date;

    constructor(reservationIdentifier:string,reservationStart: Date,reservationEnd: Date){
        this.reservationIdentifier = reservationIdentifier;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;

    }
}