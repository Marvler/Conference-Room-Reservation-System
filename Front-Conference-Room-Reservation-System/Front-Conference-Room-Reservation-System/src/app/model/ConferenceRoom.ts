import { Reservation } from "./Reservation";

export class ConferenceRoom {
    conferenceRoomIdentifier:string;
    level:number;
    availability:boolean;
    numberOfSeats:number;
    numberOfStandings:number;
    reservations: Reservation[]=[];

    constructor(conferenceRoomIdentifier:string, level:number, numberOfSeats:number, numberOfStandings:number, reservations: Reservation[]){
        this.conferenceRoomIdentifier = conferenceRoomIdentifier;
        this.level = level;
        this.availability = true;
        this.numberOfSeats = numberOfSeats;
        this.numberOfStandings = numberOfStandings;

        }
    
}