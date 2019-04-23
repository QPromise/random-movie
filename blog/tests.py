from django.test import TestCase

# Create your tests here.
from django.core.paginator import Paginator
item_list = ['john', 'paul', 'george', 'ringo']
p = Paginator(item_list, 3)
page2 = p.page(1)
print(page2.object_list)