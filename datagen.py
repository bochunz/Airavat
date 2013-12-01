from random import choice
from random import random
from datetime import datetime
from datetime import date
from datetime import timedelta
import time

fruits = ['apple', 'cherry', 'orange']

start_date = datetime.fromtimestamp(time.mktime(time.strptime("2013-09-01", "%Y-%m-%d"))).date()
#end_date = datetime.fromtimestamp(time.mktime(time.strptime("2013-12-31", "%Y-%m-%d"))).date()

dates = [start_date + timedelta(i) for i in range(70)]

#print dates
#print start_date.strftime("%Y-%m-%d\t%a")
for i in xrange(50000):
    print choice(fruits) + "\t" + choice(dates).strftime("%Y-%m-%d\t%a") + "\t" + "%.2f" % (random()*10)