# Big Daddy #
### Things Everyone Cares About:
#### Current Ports: 
Thing | Bot
--------|-----------------------
left side talons (looking from the back to the intake) | `1 & 2`
right side talons (looking from the back to the intake) |  `3 & 4`
intake spin talon | `5` 
intake pivot talon | `6`
intake encoder | `0,1`
intake IR sensors | `6,7`
right drivetrain encoder | `2,3`
left drivetrain encoder | `4,5`
PCM | `20`
catapult solenoids | '2,3'

### Control Scheme:
#### Controller -
- Arcade Drive 
  - left stick controls forward and backwards, right stick controls left and right

#### Joystick -
- Defaults to Vertical Position
- Intake: `7` 
  - will stop intaking while IR sensor is tripped
- Outwards Outtake: `6`
- Ground Position: `3`
- Intake Into Catapult: `2`
- Shoot: `trigger`
- Zero the Intake: `5`

![squidward future](https://s-media-cache-ak0.pinimg.com/236x/bc/f8/97/bcf89787b054ba512c777abfad16fff4.jpg)
- [x] tune intake PID if necessary
- [x] make catapult not be the t-shirt cannon
- [x] update sensor base as necessary
- [ ] voltage sensing for intaking balls to replace intake button
- [ ] timeout command
- [x] commands for crossing defenses in autonomous
- [ ] vision
- [x] fine tune turn mode for shooting
